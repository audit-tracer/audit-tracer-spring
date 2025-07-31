package com.audittracer.client.spring;

import com.github.f4b6a3.ulid.UlidCreator;

import java.io.Serial;
import java.io.Serializable;
import java.time.Instant;
import java.util.Map;

@SuppressWarnings("all")
public class Action implements Comparable<Action>, Serializable {
  @Serial
  private static final long serialVersionUID = 1L;

  private final String id;
  private final String action;
  private final AuditType auditType;
  private final String targetType;
  private final String targetId;
  private final String targetName;
  private final Map<String, String> metadata;
  private final Instant timestamp;


  public Action(
          final String action,
          final AuditType auditType,
          final String targetType,
          final String targetId,
          final String targetName,
          final Map<String, String> metadata) {
    this.id = UlidCreator.getUlid().toString();
    this.timestamp = Instant.now();
    this.action = action;
    this.auditType = auditType;
    this.targetType = targetType;
    this.targetId = targetId;
    this.targetName = targetName;
    this.metadata = metadata;
  }

  public String getId() {
    return id;
  }

  public String getAction() {
    return action;
  }

  public AuditType getAuditType() {
    return auditType;
  }

  public String getTargetType() {
    return targetType;
  }

  public String getTargetId() {
    return targetId;
  }

  public String getTargetName() {
    return targetName;
  }

  public Map<String, String> getMetadata() {
    return metadata;
  }

  public Instant getTimestamp() {
    return timestamp;
  }

  @Override
  public String toString() {
    return "%s - %s".formatted(Instant.now(), action);
  }

  @Override
  public int compareTo(final Action other) {
    return this.id.compareTo(other.id);
  }
}
