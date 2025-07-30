package com.audittracer.client.spring.dto;

import com.audittracer.client.spring.Action;

import java.time.Instant;
import java.util.List;

public record AuditBatchRequest(List<Action> actions, String batchId, Instant timestamp) {
}
