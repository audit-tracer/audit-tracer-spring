package com.audittracer.client.spring.exception;

@SuppressWarnings("unused")
public class AuditException extends RuntimeException {
  public AuditException(String message) {
    super(message);
  }
}
