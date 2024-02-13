package com.bogoliubova.training_service.aspect;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Arrays;

@Aspect
@Component
@Slf4j
public class LoggingAspect {
    @Pointcut("execution(public * com.bogoliubova.training_service.controller.*.*(..))")
    public void controllerLog() {
    }

    @Pointcut("execution(public * com.bogoliubova.training_service.service.*.*(..))")
    public void serviceLog() {
    }

    @Before("controllerLog()")
    public void doBeforeController(JoinPoint joinPoint) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        log.info("***************************************************NEW REQUEST:\n" +
                        "IP : {}\n" +
                        "URL : {}\n" +
                        "HTTP_METHOD : {}\n" +
                        "CONTROLLER_METHOD : {}.{}",
                request.getRemoteAddr(),
                request.getRequestURL(),
                request.getMethod(),
                joinPoint.getSignature().getDeclaringType(),
                joinPoint.getSignature().getName());
    }

    @Before("serviceLog()")
    public void doBeforeService(JoinPoint joinPoint) {
        log.info("***************************************RUN SERVICE:\n" + "SERVICE_METHOD : {}.{}",
                joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName());
    }

    @AfterReturning(returning = "returnObject", pointcut = "controllerLog()")
    public void doAfterReturning(Object returnObject) {
        log.info("\n*********************************************Return value from controller: {}\n" + "END OF CONTROLLER REQUEST", returnObject);
    }

    @AfterThrowing(throwing = "ex", pointcut = "controllerLog()")
    public void throwException(JoinPoint joinPoint, Exception ex) {
        log.error("**************************************************Controller request throw an exception. Cause - {}. {}",
                Arrays.toString(joinPoint.getArgs()), ex.getMessage());
    }

    @AfterReturning(returning = "returnObject", pointcut = "serviceLog()")
    public void doAfterReturningService(Object returnObject) {
        log.info("\n*****************************************************Return value from service: {}\n" + "END OF SERVICE METHOD", returnObject);
    }

    @AfterThrowing(throwing = "ex", pointcut = "serviceLog()")
    public void throwExceptionService(JoinPoint joinPoint, Exception ex) {
        log.error("**********************************************************Service method throw an exception. Cause - {}. {}",
                Arrays.toString(joinPoint.getArgs()), ex.getMessage());
    }

}
