package com.audittracer.client.spring.annotation;

import com.audittracer.client.spring.AuditType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Audit {
  AuditType type() default AuditType.APP;

  String action();

  String targetType();

  String targetIdExpression() default "";

  String targetNameExpression() default "";

  String[] metadata() default {};
}
