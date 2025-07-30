package com.audittracer.client.spring.config.properties.queue;

import org.springframework.boot.context.properties.ConfigurationProperties;

import static com.audittracer.client.spring.AuditTracerName.AUDIT_TRACER_QUEUE;

@ConfigurationProperties(AUDIT_TRACER_QUEUE)
public class QueuePropertiesConfig {
  private static final int DEFAULT_QUEUE_SIZE = 50;

  private int size;

  public QueuePropertiesConfig(final int size) {
    this.size = size;
  }

  public QueuePropertiesConfig() {
    this(DEFAULT_QUEUE_SIZE);
  }

  public int getSize() {
    return this.size;
  }

  public void setSize(final int size) {
    this.size = size;
  }
}
