package com.example.spring_boot.logging;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {
    @Before("execution(* com.example.spring_boot.controllers.*.*(..))")
    public void logBefore(JoinPoint joinPoint) {
        System.out.println("Going to execute: " + joinPoint.getSignature().getName());
    }

    @AfterReturning("execution(* com.example.spring_boot.controllers.*.*(..))")
    public void logReturning(JoinPoint joinPoint) {
        System.out.println("Return successfully: " + joinPoint.getSignature().getName());
    }

    @Around("execution(* com.example.spring_boot.controllers.*.*(..))")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("Before proceeding: " + joinPoint.getSignature().getName());
        Object result = joinPoint.proceed();
        System.out.println("After proceeding: " + joinPoint.getSignature().getName());
        return result;
    }
}
