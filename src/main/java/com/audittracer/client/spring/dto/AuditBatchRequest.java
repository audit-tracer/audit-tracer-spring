package com.audittracer.client.spring.dto;

import com.audittracer.client.spring.Action;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.Instant;
import java.util.List;

@RequiredArgsConstructor
@Data
@Builder
public class AuditBatchRequest {
  private final List<Action> actions;
  private final String batchId;
  private final Instant timestamp;
}
