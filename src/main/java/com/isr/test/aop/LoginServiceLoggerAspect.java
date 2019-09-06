package com.isr.test.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Slf4j
@Aspect
@Component
public class LoginServiceLoggerAspect {

    @Before("execution(public * com.isr.test.service.impl.*ServiceImpl.*(..))")
    public void logBeforeRestCall(JoinPoint joinPoint) throws Throwable {
        log.debug("Execution of method: {}", joinPoint.getSignature().getName());
        log.debug("Arguments: {}", Arrays.toString(joinPoint.getArgs()));
    }

    @AfterReturning(
            pointcut = "execution(public * com.isr.test.service.impl.*ServiceImpl.*(..))",
            returning = "result")
    public void logAfterReturning(Object result) {
        log.debug("Method returned value is : {}", result);
    }
}
