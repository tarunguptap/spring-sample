package com.spring.aspect;

import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Configurable;

import com.spring.exception.ValidationException;

@Configurable
@Aspect
public class ValidationExceptionAspect {


    /**
     * 
     * This Aspect intercept the method when ValidationException is throw from methods which are annoted with @ExceptionMonitor
     * which allow to handle the exception more easily as compared to pointcut modification and more readiable and
     * easier to applied the exceptional handler
     * 
     * @param validationException
     */
    @AfterThrowing(pointcut = "execution(* *(..)) && @annotation(com.spring.aspect.custom.ExceptionMonitor)", throwing = "validationException")
    public void afterThrowingAdvice(ValidationException validationException) {

    }
}
