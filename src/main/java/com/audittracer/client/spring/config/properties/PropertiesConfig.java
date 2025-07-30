package com.audittracer.client.spring.config.properties;

import com.audittracer.client.spring.config.properties.queue.QueuePropertiesConfig;
import com.audittracer.client.spring.config.properties.thread.ThreadPropertiesConfig;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;


@ConfigurationProperties("audit-tracer")
@Data
public class PropertiesConfig {
  private ThreadPropertiesConfig thread;
  private QueuePropertiesConfig queue;
  private String url = "http://localhost:3333";
  private String apiKey;
  private boolean enabled = true;
}
