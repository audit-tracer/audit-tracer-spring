package com.audittracer.client.spring;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
public class ObjectData<T> {
  private final T data;
}
