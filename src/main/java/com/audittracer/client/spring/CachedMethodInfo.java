package com.audittracer.client.spring;

import com.audittracer.client.spring.annotation.Audit;

import java.lang.reflect.Parameter;

public class CachedMethodInfo {
    final Audit auditAnnotation;
    final Parameter[] parameters;
    final String methodName;

    CachedMethodInfo(final Audit auditAnnotation, final Parameter[] parameters, final String methodName) {
      this.auditAnnotation = auditAnnotation;
      this.parameters = parameters;
      this.methodName = methodName;
    }
  }