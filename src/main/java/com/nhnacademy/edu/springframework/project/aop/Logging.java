package com.nhnacademy.edu.springframework.project.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Slf4j
@Aspect
@Component
public class Logging {
    @Around("execution(* com.nhnacademy.edu.springframework.project.service.*.*(..))")
    public Object printExecutionTime(ProceedingJoinPoint pjp) throws Throwable{
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        Object target;
        try{
            target = pjp.proceed();
        }catch (Exception e){
            throw new RuntimeException(e);
        }finally {
            stopWatch.stop();
            log.error("[{}].[{}].[{}ms]", pjp.getSignature().getDeclaringType().getSimpleName(), pjp.getSignature().getName(), stopWatch.getLastTaskTimeMillis());
        }
        return target;
    }
}
