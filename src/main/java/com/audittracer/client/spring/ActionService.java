package com.audittracer.client.spring;

import com.audittracer.client.spring.dto.AuditBatchRequest;
import com.audittracer.client.spring.config.properties.PropertiesConfig;
import com.github.f4b6a3.ulid.Ulid;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

@Slf4j
@Service
@Lazy
@ConditionalOnProperty(prefix = "audit-tracer", name = "enabled", matchIfMissing = true)
public class ActionService implements DisposableBean {
  private final PropertiesConfig config;
  private final RestTemplate restTemplate;
  private final BlockingQueue<Action> actionQueue;
  private final ScheduledExecutorService batchProcessor;
  private final ExecutorService httpExecutor;
  private final AtomicBoolean isShutdown = new AtomicBoolean(false);

  private static final Duration FLUSH_INTERVAL = Duration.ofSeconds(1);

  @PostConstruct
  public void init() {
    log.warn("ActionService has been initialized");
  }

  public ActionService(
          final @NotNull PropertiesConfig config,
          final @NotNull RestTemplate restTemplate,
          @Qualifier("batch") final @NotNull ScheduledExecutorService batchProcessor,
          @Qualifier("httpExecutor") final @NotNull ExecutorService httpExecutor) {
    this.config = config;
    this.restTemplate = restTemplate;
    this.batchProcessor = batchProcessor;
    this.httpExecutor = httpExecutor;
    this.actionQueue = new LinkedBlockingQueue<>(config.getQueue().getSize());

    this.startBatchProcessor();
  }

  public void processAction(final @NotNull Action action) {
    if (this.isShutdown.get()) {
      log.warn("ActionService shutting down, dropping action: {}", action.getAction());
      return;
    }

    final boolean offered = this.actionQueue.offer(action);
    if (!offered) {
      log.error("Action queue full, dropping action: {}", action.getAction());
      // TODO: Implement fallback mechanism (disk persistence)
    }
  }

  private void startBatchProcessor() {
    this.batchProcessor.scheduleWithFixedDelay(
            this::processBatch,
            FLUSH_INTERVAL.toMillis(),
            FLUSH_INTERVAL.toMillis(),
            TimeUnit.MILLISECONDS
    );
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
          backoff = @Backoff(value = 1_500L, multiplier = 2)
  )
  private boolean sendBatch(List<Action> batch) {
    final HttpHeaders headers = new HttpHeaders();
    headers.set("X-API-KEY", this.config.getApiKey());
    headers.setContentType(MediaType.APPLICATION_JSON);

    final AuditBatchRequest request = AuditBatchRequest.builder()
            .actions(batch)
            .batchId(Ulid.fast().toString())
            .timestamp(Instant.now())
            .build();

    final HttpEntity<AuditBatchRequest> entity = new HttpEntity<>(request, headers);

    final ResponseEntity<Boolean> response = restTemplate.postForEntity(
            config.getUrl() + "/api/v1/audit/batch",
            entity,
            Boolean.class);

    return response.getStatusCode().is2xxSuccessful();
  }

  private void handleCompletedBatch(
          final @NotNull Boolean success,
          final @Nullable Throwable throwable,
          final @NotNull List<Action> batch) {
    if (throwable != null) {
      log.error("Failed to send audit batch: {}", throwable.getMessage());
      handleFailedBatch(batch);
    } else if (success) {
      log.debug("Successfully sent batch of {} actions", batch.size());
    }
  }

  private void handleFailedBatch(List<Action> batch) {
    // TODO: Implement dead letter queue or disk persistence
    log.error("Permanently failed to send batch of {} actions", batch.size());
  }

  @Override
  public void destroy() {
    log.info("Shutting down ActionService...");
    this.isShutdown.set(true);
    this.processBatch();

    this.shutdownExecutors();
    log.info("ActionService shutdown complete");
  }


  private void shutdownExecutors() {
    this.batchProcessor.shutdown();
    this.httpExecutor.shutdown();

    try {
      if (!this.batchProcessor.awaitTermination(10, TimeUnit.SECONDS)) {
        this.batchProcessor.shutdownNow();
      }
      if (!this.httpExecutor.awaitTermination(30, TimeUnit.SECONDS)) {
        this.httpExecutor.shutdownNow();
      }
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
      this.batchProcessor.shutdownNow();
      this.httpExecutor.shutdownNow();
    }
  }
}
