package com.masamiaoi.aop.authIdentity;

import java.lang.annotation.*;

/**
 * 自定义注解
 *
 * @author shenruiyan
 */
@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AopLogger {

    /**
     * 描述
     *
     * @return
     */
    String describe() default "";
}