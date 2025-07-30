package com.audittracer.client.spring.config.properties.thread;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("audit-tracer.thread")
@Getter
@Setter
public class ThreadPropertiesConfig {
  private ThreadType type = ThreadType.FIXED;
  private int size = 1;
}
