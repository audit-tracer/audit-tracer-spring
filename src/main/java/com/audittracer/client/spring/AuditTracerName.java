package com.audittracer.client.spring;

public final class AuditTracerName {
  public static final String BEAN_NAME = "com.audittracer.client.spring";
  public static final String CONFIG_BASE = "audit-tracer";
  public static final String AUDIT_TRACER_QUEUE = "audit-tracer.queue";
  public static final String AUDIT_TRACER_THREAD = "audit-tracer.thread";
  public static final String AUDIT_TRACER_ENABLED_FLAG = "enabled";
  public static final String HTTP_EXECUTOR_BEAN = "executor.http";
  public static final String BATCH_BEAN = "executor.batch";
  public static final String HEADER_API_KEY = "X-API-KEY";

  private AuditTracerName() {
  }
}
