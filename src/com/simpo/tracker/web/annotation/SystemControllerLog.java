package com.simpo.tracker.web.annotation;

import java.lang.annotation.*;


/**
 * 自定义注解 拦截Controller
 * @author yu
 */

@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented

public @interface SystemControllerLog {

    String description() default "";

}
