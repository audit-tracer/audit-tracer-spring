package com.audittracer.client.spring.config;

import com.audittracer.client.spring.config.properties.thread.ThreadPropertiesConfig;
import com.audittracer.client.spring.config.properties.thread.ThreadType;
import org.jetbrains.annotations.NotNull;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.SpelCompilerMode;
import org.springframework.expression.spel.SpelParserConfiguration;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import static com.audittracer.client.spring.AuditTracerName.AUDIT_TRACER_ENABLED_FLAG;
import static com.audittracer.client.spring.AuditTracerName.BATCH_BEAN;
import static com.audittracer.client.spring.AuditTracerName.CONFIG_BASE;
import static com.audittracer.client.spring.AuditTracerName.HTTP_EXECUTOR_BEAN;

@Component
@ConditionalOnProperty(
        prefix = CONFIG_BASE,
        name = AUDIT_TRACER_ENABLED_FLAG,
        matchIfMissing = true
)
public class ApplicationConfiguration {
  private final ThreadPropertiesConfig threadConfig;

  public ApplicationConfiguration(final @NotNull ThreadPropertiesConfig threadConfig) {
    this.threadConfig = threadConfig;
  }

  @Bean
  public @NotNull RestTemplate provideRestTemplate() {
    return new RestTemplate();
  }

  @Bean
  public @NotNull ExpressionParser provideExpressionParser(final SpelParserConfiguration config) {
    return new SpelExpressionParser(config);
  }

  @Bean
  public @NotNull SpelParserConfiguration provideSpelParserConfiguration() {
    return new SpelParserConfiguration(SpelCompilerMode.IMMEDIATE, this.getClass().getClassLoader());
  }

  @Bean(HTTP_EXECUTOR_BEAN)
  public @NotNull ExecutorService provideThreadPoolExecutor() {
    return switch (threadConfig.getType()) {
      case ThreadType.FIXED -> Executors.newFixedThreadPool(threadConfig.getSize());
      case ThreadType.CACHE -> Executors.newCachedThreadPool();
    };
  }

  @Bean(BATCH_BEAN)
  public @NotNull ScheduledExecutorService provideBatchExecutor() {
    return Executors.newSingleThreadScheduledExecutor();
  }
}
