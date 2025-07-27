package com.audittracer.client.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.SpelCompilerMode;
import org.springframework.expression.spel.SpelParserConfiguration;
import org.springframework.expression.spel.standard.SpelExpressionParser;

@Configuration
public class ExpressionConfig {
  @Bean
  public ExpressionParser provideExpressionParser(final SpelParserConfiguration config) {
    return new SpelExpressionParser(config);
  }

  @Bean
  public SpelParserConfiguration provideSpelParserConfiguration() {
    return new SpelParserConfiguration(SpelCompilerMode.IMMEDIATE, this.getClass().getClassLoader());
  }
}
