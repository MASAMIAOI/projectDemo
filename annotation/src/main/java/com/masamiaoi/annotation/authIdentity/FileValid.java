package com.masamiaoi.annotation.authIdentity;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author shenruiyan
 * @version 1.0
 * @Description: 文件切面开关
 * @date 2023/2/23 - 10:11
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface FileValid {
    
}
