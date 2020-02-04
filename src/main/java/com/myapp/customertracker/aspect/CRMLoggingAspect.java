package com.myapp.customertracker.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Aspect
@Component
public class CRMLoggingAspect {

    private final Logger myLogger = Logger.getLogger(getClass().getName());

    @Pointcut("execution(* com.myapp.customertracker.controller.*.*(..))")
    private void forControllerPackage() {
    }

    @Pointcut("execution(* com.myapp.customertracker.service.*.*(..))")
    private void forServicePackage() {
    }

    @Pointcut("forControllerPackage() || forServicePackage()")
    private void forAppFlow() {
    }

    @Before("forAppFlow()")
    public void before(JoinPoint joinPoint) {
        String theMethod = joinPoint.getSignature().toString();
        myLogger.info("=====>> in @Before: calling method: " + theMethod);

        Object[] args = joinPoint.getArgs();
        for (Object arg : args) {
            myLogger.info("=====>> argument: " + arg);
        }
    }

    @AfterReturning(pointcut = "forAppFlow()", returning = "result")
    public void afterReturning(JoinPoint joinPoint, Object result) {
        String theMethod = joinPoint.getSignature().toString();
        myLogger.info("=====>> in @AfterReturning: from method: " + theMethod);

        myLogger.info("=====>> result: " + result);
    }
}