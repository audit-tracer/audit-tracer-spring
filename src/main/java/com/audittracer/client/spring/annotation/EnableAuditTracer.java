package com.audittracer.client.spring.annotation;

import com.audittracer.client.spring.config.properties.PropertiesConfig;
import com.audittracer.client.spring.config.properties.queue.QueuePropertiesConfig;
import com.audittracer.client.spring.config.properties.thread.ThreadPropertiesConfig;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Configuration
@EnableConfigurationProperties({ThreadPropertiesConfig.class, QueuePropertiesConfig.class, PropertiesConfig.class})
@ComponentScan("com.audittracer.client.spring")
public @interface EnableAuditTracer {
}