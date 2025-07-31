package com.audittracer.client.spring.config.properties.thread;

import org.springframework.boot.context.properties.ConfigurationProperties;

import static com.audittracer.client.spring.AuditTracerName.AUDIT_TRACER_THREAD;

@ConfigurationProperties(AUDIT_TRACER_THREAD)
@SuppressWarnings("unused")
public class ThreadPropertiesConfig {
  private static final int DEFAULT_THREAD_SIZE = 1;

  private ThreadType type;
  private int size;

  public ThreadPropertiesConfig(final ThreadType type, final int size) {
    this.type = type;
    this.size = size;
  }

  public ThreadPropertiesConfig() {
    this(ThreadType.FIXED, DEFAULT_THREAD_SIZE);
  }

  public ThreadType getType() {
    return type;
  }

  public int getSize() {
    return size;
  }

  public void setType(ThreadType type) {
    this.type = type;
  }

  public void setSize(int size) {
    this.size = size;
  }
}
