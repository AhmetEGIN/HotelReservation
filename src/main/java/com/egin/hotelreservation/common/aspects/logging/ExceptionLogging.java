package com.egin.hotelreservation.common.aspects.logging;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Slf4j
@Component
public class ExceptionLogging {


    @Pointcut("execution(* com.egin.hotelreservation.common.exception.handler.GloabalExceptionHandler.handle*(..))")
    public void pointcutForAfterThrowingException() {};



    @AfterThrowing(pointcut = "pointcutForAfterThrowingException()", throwing = "throwable")
    public void logAfterThrowingException(JoinPoint joinPoint, Throwable throwable) {
        log.info("{} class'ı çalışırken hata: {} ", joinPoint.getClass().getSimpleName() , throwable.getMessage());

    }

}
