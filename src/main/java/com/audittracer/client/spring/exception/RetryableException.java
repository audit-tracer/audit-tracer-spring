package com.audittracer.client.spring.exception;

@SuppressWarnings("unused")
public class RetryableException extends RuntimeException {
  public RetryableException(final String message) {
    super(message);
  }
}
