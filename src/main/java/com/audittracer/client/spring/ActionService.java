package com.audittracer.client.spring;

import com.audittracer.client.spring.config.properties.Environment;
import com.audittracer.client.spring.dto.AuditBatchRequest;
import com.audittracer.client.spring.config.properties.PropertiesConfig;
import com.audittracer.client.spring.exception.AuditException;
import com.github.f4b6a3.ulid.Ulid;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

import static com.audittracer.client.spring.AuditTracerName.AUDIT_TRACER_ENABLED_FLAG;
import static com.audittracer.client.spring.AuditTracerName.HTTP_EXECUTOR_BEAN;
import static com.audittracer.client.spring.AuditTracerName.BATCH_BEAN;
import static com.audittracer.client.spring.AuditTracerName.CONFIG_BASE;
import static com.audittracer.client.spring.AuditTracerName.HEADER_API_KEY;
import static com.audittracer.client.spring.AuditTracerName.LOG_PREFIX;

@Service
@ConditionalOnProperty(prefix = CONFIG_BASE, name = AUDIT_TRACER_ENABLED_FLAG, matchIfMissing = true)
@SuppressWarnings("all")
public class ActionService implements DisposableBean {
  private static final Logger LOGGER = LoggerFactory.getLogger(ActionService.class);

  private static final long BATCH_BACKOFF_TIMEOUT = 1500L;
  private static final long BATCH_MULTIPLIER = 2;
  private static final long HTTP_THREAD_TIMEOUT = 30;
  private static final long BATCH_THREAD_TIMEOUT = 10;

  private final PropertiesConfig config;
  private final RestTemplate restTemplate;
  private final BlockingQueue<Action> actionQueue;
  private final ExecutorService batchProcessor;
  private final ExecutorService httpExecutor;
  private final AtomicBoolean isShutdown = new AtomicBoolean(false);

  public ActionService(
          final PropertiesConfig config,
          final RestTemplate restTemplate,
          @Qualifier(BATCH_BEAN) final ExecutorService batchProcessor,
          @Qualifier(HTTP_EXECUTOR_BEAN) final ExecutorService httpExecutor) {
    LOGGER.debug("{} - com.audittracer.client.spring.ActionService::init", LOG_PREFIX);

    this.config = config;
    this.restTemplate = restTemplate;
    this.batchProcessor = batchProcessor;
    this.httpExecutor = httpExecutor;
    this.actionQueue = new LinkedBlockingQueue<>(config.getQueue().getSize());
  }

  @PostConstruct
  public void init() {
    final HttpHeaders headers = new HttpHeaders();
    headers.set(HEADER_API_KEY, this.config.getApiKey());
    headers.setContentType(MediaType.APPLICATION_JSON);

    final HttpEntity<AuditBatchRequest> entity = new HttpEntity<>(headers);

    final var response = this.restTemplate.
            postForEntity(
                    "%s/api/ingestion/check".formatted(config.getUrl()),
                    entity,
                    Boolean.class);

    if (!response.getStatusCode().is2xxSuccessful()) {
      LOGGER.error("{} - API KEY verification error", LOG_PREFIX);
      throw new AuditException("API KEY verification failed");
    }

    LOGGER.debug("{} - API KEY verified", LOG_PREFIX);
  }

  public void processAction(final Action action) {
    if (config.getEnvironment() == Environment.LOCAL) {
      LOGGER.info("{} - {}", LOG_PREFIX, action.toString());
      return;
    }

    if (this.isShutdown.get()) {
      LOGGER.warn("ActionService shutting down, dropping action: {}", action.getAction());
      return;
    }

    try {
      this.actionQueue.put(action);

      if (this.actionQueue.size() >= this.config.getQueue().getSize()) {
        this.batchProcessor.execute(this::processBatch);
      }

    } catch (final InterruptedException e) {
      Thread.currentThread().interrupt(); // prevent zombi threads
      LOGGER.error("Interrupted while queuing action: {}", action.getAction());
    }
  }

  private void processBatch() {
    if (this.isShutdown.get()) {
      return;
    }

    final List<Action> batch = new ArrayList<>(config.getQueue().getSize());
    this.actionQueue.drainTo(batch, config.getQueue().getSize());

    if (!batch.isEmpty()) {
      sendBatchAsync(batch);
    }
  }

  private void sendBatchAsync(List<Action> batch) {
    CompletableFuture
            .supplyAsync(() -> sendBatch(batch), this.httpExecutor)
            .whenComplete((success, throwable) -> this.handleCompletedBatch(success, throwable, batch));
  }

  @Retryable(
          retryFor = Throwable.class,
          backoff = @Backoff(value = BATCH_BACKOFF_TIMEOUT, multiplier = BATCH_MULTIPLIER),
          recover = "recoverSendBatch"
  )
  private boolean sendBatch(List<Action> batch) {
    final HttpHeaders headers = new HttpHeaders();
    headers.set(HEADER_API_KEY, this.config.getApiKey());
    headers.setContentType(MediaType.APPLICATION_JSON);

    final AuditBatchRequest request = new AuditBatchRequest(batch, Ulid.fast().toString(), Instant.now());

    final HttpEntity<AuditBatchRequest> entity = new HttpEntity<>(request, headers);

    final ResponseEntity<Boolean> response = restTemplate.postForEntity(
            config.getUrl() + "/api/audit/batch",
            entity,
            Boolean.class);

    return response.getStatusCode().is2xxSuccessful();
  }


  @Recover
  public boolean recoverSendBatch(final Throwable throwable, final List<Action> actions) {
    LOGGER.error("All retry attempts failed for batch of {} actions: {}", actions.size(), throwable.getMessage());
    handleFailedBatch(actions);
    return false;
  }

  private void handleCompletedBatch(
          final boolean success,
          final Throwable throwable,
          final List<Action> batch) {
    if (throwable != null) {
      LOGGER.error("Failed to send audit batch: {}", throwable.getMessage());
      handleFailedBatch(batch);
    } else if (success) {
      LOGGER.debug("Successfully sent batch of {} actions", batch.size());
    }
  }

  private void handleFailedBatch(List<Action> batch) {
    // TODO: Implement dead letter queue or disk persistence
    LOGGER.error("Permanently failed to send batch of {} actions", batch.size());
  }

  @Override
  public void destroy() {
    LOGGER.info("Shutting down ActionService...");
    this.isShutdown.set(true);

    while (!this.actionQueue.isEmpty()) {
      processBatch();
    }

    this.shutdownExecutors();
    LOGGER.info("ActionService shutdown complete");
  }


  private void shutdownExecutors() {
    this.batchProcessor.shutdown();
    this.httpExecutor.shutdown();

    try {
      if (!this.batchProcessor.awaitTermination(BATCH_THREAD_TIMEOUT, TimeUnit.SECONDS)) {
        this.batchProcessor.shutdownNow();
      }
      if (!this.httpExecutor.awaitTermination(HTTP_THREAD_TIMEOUT, TimeUnit.SECONDS)) {
        this.httpExecutor.shutdownNow();
      }
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
      this.batchProcessor.shutdownNow();
      this.httpExecutor.shutdownNow();
    }
  }
}
