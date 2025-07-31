package com.audittracer.client.spring.exception;

public class RetryableException extends RuntimeException {
  public RetryableException(final String message) {
    super(message);
  }
}
