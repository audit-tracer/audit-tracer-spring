package com.audittracer.client.spring.exception;

public class AuditException extends RuntimeException {
  public AuditException(String message) {
    super(message);
  }
}
