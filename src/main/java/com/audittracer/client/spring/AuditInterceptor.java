package com.audittracer.client.spring;

import com.audittracer.client.spring.annotation.Audit;
import com.audittracer.client.spring.annotation.AuditParam;
import com.audittracer.client.spring.exception.FieldEmptyException;

import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static com.audittracer.client.spring.AuditTracerName.AUDIT_TRACER_ENABLED_FLAG;
import static com.audittracer.client.spring.AuditTracerName.CONFIG_BASE;

@Component
@Aspect
@ConditionalOnProperty(
        prefix = CONFIG_BASE,
        name = AUDIT_TRACER_ENABLED_FLAG,
        matchIfMissing = true
)
public class AuditInterceptor {
  private static final Logger LOGGER = LoggerFactory.getLogger(AuditInterceptor.class);

  private final @NotNull ActionService actionService;
  private final @NotNull ExpressionParser compiledParser;
  private final @NotNull ConcurrentHashMap<String, Expression> expressionCache;
  private final @NotNull ConcurrentHashMap<Method, CachedMethodInfo> methodCache;

  public AuditInterceptor(
          final @NotNull ActionService actionService,
          final @NotNull ExpressionParser compiledParser) {
    this.actionService = actionService;
    this.compiledParser = compiledParser;
    this.expressionCache = new ConcurrentHashMap<>(256);
    this.methodCache = new ConcurrentHashMap<>(128);
  }

  @PostConstruct
  public void init() {
    LOGGER.debug("[AUDIT-TRACER] - com.audittracer.client.spring.AuditInterceptor::init");
  }

  @AfterReturning(
          pointcut = "@annotation(com.audittracer.client.spring.annotation.Audit)",
          returning = "methodResult"
  )
  public void invoke(final JoinPoint joinPoint, final Object methodResult) {
    final MethodSignature signature = (MethodSignature) joinPoint.getSignature();
    final Method method = signature.getMethod();

    final CachedMethodInfo methodInfo = getOrCacheMethodInfo(method);

    if (methodInfo.auditAnnotation == null) {
      return;
    }

    final StandardEvaluationContext context = buildSpELContext(joinPoint.getArgs(), methodInfo, methodResult);

    processAuditAnnotation(methodInfo.auditAnnotation, context);
  }

  private CachedMethodInfo getOrCacheMethodInfo(Method method) {
    return methodCache.computeIfAbsent(method, m -> {
      final Audit audit = m.getAnnotation(Audit.class);
      return new CachedMethodInfo(audit, m.getParameters(), method.getName());
    });
  }

  private StandardEvaluationContext buildSpELContext(
          final Object[] args,
          final CachedMethodInfo methodInfo,
          final Object methodResult) {

    final StandardEvaluationContext context = new StandardEvaluationContext();

    context.setVariable("result", methodResult);
    context.setVariable("methodName", methodInfo.methodName);

    this.processMethodParameters(args, methodInfo.parameters, context);
    this.addSecurityContext(context);
    this.addHttpRequestContext(context);

    return context;
  }

  private void processMethodParameters(Object[] args, Parameter[] parameters, StandardEvaluationContext context) {
    for (int i = 0; i < parameters.length && i < args.length; i++) {
      final Parameter param = parameters[i];
      final Object arg = args[i];

      // Set parameter by name - exactly like Spring does it
      context.setVariable(param.getName(), arg);

      // Process AuditParam annotation - custom parameter naming
      final AuditParam auditParam = param.getAnnotation(AuditParam.class);
      if (auditParam != null && StringUtils.hasText(auditParam.value())) {
        context.setVariable(auditParam.value(), arg);
      }
    }
  }

  private void addSecurityContext(StandardEvaluationContext context) {
    try {
      final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
      if (authentication != null) {
        // Standard Spring Security variables
        context.setVariable("authentication", authentication);
        context.setVariable("principal", authentication.getPrincipal());
        context.setVariable("securityContext", SecurityContextHolder.getContext());

        // Convenient shortcuts
        context.setVariable("username", authentication.getName());
        context.setVariable("authorities", authentication.getAuthorities());
        context.setVariable("isAuthenticated", authentication.isAuthenticated());
      } else {
        context.setVariable("username", "anonymous");
        context.setVariable("isAuthenticated", false);
      }
    } catch (Exception e) {
      LOGGER.warn("Security context not available: {}", e.getMessage());
      context.setVariable("username", "system");
      context.setVariable("isAuthenticated", false);
    }
  }

  private void addHttpRequestContext(StandardEvaluationContext context) {
    try {
      final ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
      if (attributes != null) {
        final HttpServletRequest request = attributes.getRequest();

        // Standard Spring web variables - exactly like @Value works
        context.setVariable("request", request);
        context.setVariable("session", request.getSession(false));

        // Convenient shortcuts
        context.setVariable("remoteAddr", request.getRemoteAddr());
        context.setVariable("userAgent", request.getHeader("User-Agent"));
        context.setVariable("requestMethod", request.getMethod());
        context.setVariable("requestURI", request.getRequestURI());
        context.setVariable("sessionId",
                request.getSession(false) != null ? request.getSession().getId() : null);
      }
    } catch (final Exception e) {
      LOGGER.warn("HTTP request context not available: {}", e.getMessage());
    }
  }

  private void processAuditAnnotation(Audit auditAnnotation, StandardEvaluationContext context) {
    final String evaluatedAction = evaluateSpELExpression(auditAnnotation.action(), context, "action");
    if (!StringUtils.hasText(evaluatedAction)) {
      throw new FieldEmptyException("Audit action cannot be empty after evaluation.");
    }

    final String evaluatedTargetType = evaluateSpELExpression(auditAnnotation.targetType(), context, "targetType");
    if (!StringUtils.hasText(evaluatedTargetType)) {
      throw new FieldEmptyException("Audit targetType cannot be empty after evaluation.");
    }

    final String evaluatedTargetId = evaluateSpELExpression(auditAnnotation.targetIdExpression(), context, "targetId");
    final String evaluatedTargetName = evaluateSpELExpression(auditAnnotation.targetNameExpression(), context, "targetName");

    final Map<String, String> evaluatedMetadata = processMetadata(auditAnnotation.metadata(), context);

    final Action actionObj = new Action(evaluatedAction, auditAnnotation.type(), evaluatedTargetType, evaluatedTargetId, evaluatedTargetName, evaluatedMetadata);

    this.actionService.processAction(actionObj);
  }

  private Map<String, String> processMetadata(String[] metadataExpressions, StandardEvaluationContext context) {
    if (metadataExpressions.length == 0) {
      return Map.of();
    }

    final Map<String, String> evaluatedMetadata = new HashMap<>(metadataExpressions.length);

    for (String metaItem : metadataExpressions) {
      final int equalIndex = metaItem.indexOf('=');
      if (equalIndex > 0 && equalIndex < metaItem.length() - 1) {
        final String key = metaItem.substring(0, equalIndex).trim();
        final String valueExpression = metaItem.substring(equalIndex + 1).trim();
        final String evaluatedValue = evaluateSpELExpression(valueExpression, context, "metadata." + key);

        if (StringUtils.hasText(evaluatedValue)) {
          evaluatedMetadata.put(key, evaluatedValue);
        }
      }
    }

    return evaluatedMetadata;
  }

  private String evaluateSpELExpression(String expression, StandardEvaluationContext context, String fieldName) {
    if (!StringUtils.hasText(expression)) {
      return null;
    }

    // Check if it's a SpEL expression with #{} wrapper
    if (isSpELExpression(expression)) {
      try {
        // Extract SpEL expression from #{...} wrapper
        final String spelExpression = extractSpELExpression(expression);
        final Expression compiledExpression = getCompiledExpression(spelExpression);
        final Object value = compiledExpression.getValue(context);
        return value != null ? value.toString() : null;
      } catch (final Exception e) {
        LOGGER.error("SpEL evaluation failed for '{}' in field '{}': {}",
                expression, fieldName, e.getMessage());
        return "[SpEL Error: " + fieldName + "]";
      }
    }

    // Return as literal value if no SpEL wrapper
    return expression;
  }

  private boolean isSpELExpression(String expression) {
    // Standard Spring SpEL detection - exactly like @Value
    return (expression.startsWith("#{") && expression.endsWith("}")) ||
            (expression.startsWith("${") && expression.endsWith("}"));
  }

  private String extractSpELExpression(String wrappedExpression) {
    // Extract content between #{...} or ${...} - exactly like Spring does
    if (wrappedExpression.startsWith("#{") || wrappedExpression.startsWith("${")) {
      return wrappedExpression.substring(2, wrappedExpression.length() - 1);
    }
    return wrappedExpression;
  }

  private Expression getCompiledExpression(String expression) {
    return expressionCache.computeIfAbsent(expression, expr -> {
      try {
        return compiledParser.parseExpression(expr);
      } catch (Exception e) {
        LOGGER.error("Failed to parse SpEL expression: '{}' - {}", expr, e.getMessage());
        throw e;
      }
    });
  }

  // Cache structure for method information
  private static class CachedMethodInfo {
    final Audit auditAnnotation;
    final Parameter[] parameters;
    final String methodName;

    CachedMethodInfo(Audit auditAnnotation, Parameter[] parameters, String methodName) {
      this.auditAnnotation = auditAnnotation;
      this.parameters = parameters;
      this.methodName = methodName;
    }
  }
}