package com.audittracer.client.spring;

import lombok.Builder;
import lombok.Data;
import org.jetbrains.annotations.NotNull;

import java.io.Serial;
import java.io.Serializable;
import java.time.Instant;
import java.util.Map;

@Data
@Builder
public class Action implements Comparable<Action>, Serializable {
  @Serial
  private static final long serialVersionUID = 1L;

  private final String id;
  private final String action;
  private final AuditType auditType;
  private final String targetId;
  private final Map<String, String> metadata;
  private final String targetType;
  private final String targetName;
  private final String target;

  private final Instant timestamp;
  private final String tenantId;
  private final String userId;
  private final String sessionId;
  private final String ipAddress;

  @Override
  public String toString() {
    return "%s - %s".formatted(Instant.now(), action);
  }

  @Override
  public int compareTo(final @NotNull Action other) {
    return this.id.compareTo(other.id);
  }
}
