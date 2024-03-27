package com.bogoliubova.training_service.aspect;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Arrays;
import java.util.Objects;

@Aspect
@Component
@Slf4j
public class LoggingAspect {
    @Pointcut(value = "execution(public * com.bogoliubova.training_service.controller.*.*(..))")
    public void controllerLog() {
    }

    @Pointcut(value = "execution(public * com.bogoliubova.training_service.service.*.*(..))")
    public void serviceLog() {
    }

    @Before("controllerLog()")
    public void doBeforeController(JoinPoint joinPoint) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = Objects.requireNonNull(attributes).getRequest();
        log.info("""
                        NEW REQUEST:
                        IP : {}
                        URL : {}
                        HTTP_METHOD : {}
                        CONTROLLER_METHOD : {}.{}
                        """,
                request.getRemoteAddr(),
                request.getRequestURL(),
                request.getMethod(),
                joinPoint.getSignature().getDeclaringType(),
                joinPoint.getSignature().getName());
    }

    @Before("serviceLog()")
    public void doBeforeService(JoinPoint joinPoint) {
        log.info("RUN SERVICE:\n" + "SERVICE_METHOD : {}.{}",
                joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName());
    }

    @AfterReturning(returning = "returnObject", pointcut = "controllerLog()")
    public void doAfterReturning(Object returnObject) {
        log.info("""
                Return value from controller: {}
                END OF CONTROLLER REQUEST
                """, returnObject);
    }

    @AfterThrowing(throwing = "ex", pointcut = "controllerLog()")
    public void throwException(JoinPoint joinPoint, Exception ex) {
        log.error("Controller request throw an exception. Cause - {}. {}",
                Arrays.toString(joinPoint.getArgs()), ex.getMessage());
    }

    @AfterReturning(returning = "returnObject", pointcut = "serviceLog()")
    public void doAfterReturningService(Object returnObject) {
        log.info("""
                Return value from service: {}
                END OF SERVICE METHOD
                """, returnObject);
    }

    @AfterThrowing(throwing = "ex", pointcut = "serviceLog()")
    public void throwExceptionService(JoinPoint joinPoint, Exception ex) {
        log.error("Service method throw an exception. Cause - {}. {}",
                Arrays.toString(joinPoint.getArgs()), ex.getMessage());
    }
}
