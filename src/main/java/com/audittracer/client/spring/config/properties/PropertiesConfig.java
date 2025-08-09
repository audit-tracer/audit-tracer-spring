package com.audittracer.client.spring.config.properties;

import com.audittracer.client.spring.config.properties.queue.QueuePropertiesConfig;
import com.audittracer.client.spring.config.properties.thread.ThreadPropertiesConfig;
import org.springframework.boot.context.properties.ConfigurationProperties;

import static com.audittracer.client.spring.AuditTracerName.CONFIG_BASE;

@ConfigurationProperties(CONFIG_BASE)
public class PropertiesConfig {
  private ThreadPropertiesConfig thread;
  private QueuePropertiesConfig queue;
  private String apiKey;
  private Environment environment;
  private boolean enabled = true;
  private String url = "api.audittracer.com";;

  public PropertiesConfig() {

  }

  public Environment getEnvironment() {
    return environment;
  }

  public void setEnvironment(Environment environment) {
    this.environment = environment;
  }

  public ThreadPropertiesConfig getThread() {
    return thread;
  }

  public QueuePropertiesConfig getQueue() {
    return queue;
  }

  public String getUrl() {
    return url;
  }

  public String getApiKey() {
    return apiKey;
  }

  public boolean isEnabled() {
    return enabled;
  }

  public void setThread(ThreadPropertiesConfig thread) {
    this.thread = thread;
  }

  public void setQueue(QueuePropertiesConfig queue) {
    this.queue = queue;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public void setApiKey(String apiKey) {
    this.apiKey = apiKey;
  }

  public void setEnabled(boolean enabled) {
    this.enabled = enabled;
  }
}
