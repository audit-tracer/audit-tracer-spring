package com.audittracer.client.spring;

import lombok.Builder;
import lombok.Data;

import java.time.Instant;
import java.util.Map;

@Data
@Builder
public class Action {
  private final String action;
  private final AuditType auditType;
  private final String targetId;
  private final Map<String, String> metadata;
  private final String targetType;
  private final String targetName;
  private final String target;

  @Override
  public String toString() {
    return "%s - %s".formatted(Instant.now(), action);
  }
}
