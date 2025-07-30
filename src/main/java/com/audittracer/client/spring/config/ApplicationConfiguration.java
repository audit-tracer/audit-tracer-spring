package com.audittracer.client.spring.config;

import com.audittracer.client.spring.config.properties.thread.ThreadPropertiesConfig;
import com.audittracer.client.spring.config.properties.thread.ThreadType;
import lombok.RequiredArgsConstructor;
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

@Component
@ConditionalOnProperty(prefix = "audit-tracer", name = "enabled", matchIfMissing = true)
@RequiredArgsConstructor
public class ApplicationConfiguration {
  private final ThreadPropertiesConfig threadConfig;

  @Bean
  public RestTemplate provideRestTemplate() {
    return new RestTemplate();
  }

  @Bean("sync")
  public Object provideSyncObject() {
    return new Object();
  }

  @Bean
  public ExpressionParser provideExpressionParser(final SpelParserConfiguration config) {
    return new SpelExpressionParser(config);
  }

  @Bean
  public SpelParserConfiguration provideSpelParserConfiguration() {
    return new SpelParserConfiguration(SpelCompilerMode.IMMEDIATE, this.getClass().getClassLoader());
  }

  @Bean("httpExecutor")
  public @NotNull ExecutorService provideThreadPoolExecutor() {
    final ThreadType threadType = threadConfig.getType();

    return switch (threadType) {
      case ThreadType.FIXED -> Executors.newFixedThreadPool(threadConfig.getSize());
      case ThreadType.CACHE -> Executors.newCachedThreadPool();
    };
  }

  @Bean("batch")
  public @NotNull ScheduledExecutorService provideBatchExecutor() {
    return Executors.newSingleThreadScheduledExecutor();
  }
}
