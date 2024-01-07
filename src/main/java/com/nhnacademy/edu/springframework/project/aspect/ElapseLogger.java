package com.nhnacademy.edu.springframework.project.aspect;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Aspect
@Component
public class ElapseLogger {
    private static final Log log = LogFactory.getLog(ElapseLogger.class);

    @Around("execution(* com.nhnacademy.edu.springframework.project.*.*.*(..))")
    public Object logElapse(ProceedingJoinPoint proceedingJoinPoint) {
        String className = proceedingJoinPoint.getTarget().getClass().getSimpleName();
        String methodName = proceedingJoinPoint.getSignature().getName();
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        Object result;
        try{
            result = proceedingJoinPoint.proceed();
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }finally {
            stopWatch.stop();
            log.info(className + "." + methodName + " " + stopWatch.getTotalTimeMillis() + "ms");
        }
        return result;
    }

}
