package com.audittracer.client.spring.config.properties.queue;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;


@ConfigurationProperties("audit-tracer.queue")
@Getter
@Setter
public class QueuePropertiesConfig {
  private int size = 50;
}
