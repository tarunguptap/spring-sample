package com.spring.aspect.custom;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.stereotype.Component;

/**
 * This Exception Monitor is used as ponitcut to handle the ValidationException which is much cleaner and
 * readable way to apply advice for saving transaction reason in ValidationExceptionAspect.
 * 
 * @author 
 *
 */
@Component
@Target(value = { ElementType.METHOD, ElementType.TYPE })
@Retention(value = RetentionPolicy.RUNTIME)
public @interface ExceptionMonitor {

}
