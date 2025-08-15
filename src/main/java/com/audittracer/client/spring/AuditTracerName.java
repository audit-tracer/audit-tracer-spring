package com.audittracer.client.spring;

@SuppressWarnings("all")
public final class AuditTracerName {
  public static final String BEAN_NAME = "com.audittracer.client.spring";
  public static final String CONFIG_BASE = "audit-tracer";
  public static final String AUDIT_TRACER_QUEUE = "audit-tracer.queue";
  public static final String AUDIT_TRACER_THREAD = "audit-tracer.thread";
  public static final String AUDIT_TRACER_ENABLED_FLAG = "enabled";
  public static final String HTTP_EXECUTOR_BEAN = "executor.http";
  public static final String BATCH_BEAN = "executor.batch";
  public static final String HEADER_API_KEY = "X-API-KEY";
  public static final String LOG_PREFIX = "\u001B[32m[AUDIT-TRACER]\u001B[0m";
  public static final String DEFAULT_API_URL = "https://api.audittracer.com";
  public static final String CHECK_CONNECTION_URL_FORMAT = "%s/api/ingestion/check";
  public static final String BATCH_AUDIT_URL = "%s/api/audit/batch";

  private AuditTracerName() {
  }
}
