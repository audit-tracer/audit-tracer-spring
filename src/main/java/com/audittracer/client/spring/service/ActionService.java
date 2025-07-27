package com.audittracer.client.spring.service;

import com.audittracer.client.spring.Action;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@Lazy
public class ActionService {

  public void send(final Action action) {
    log.warn(action.toString());
  }
}
