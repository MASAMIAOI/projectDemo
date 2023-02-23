package com.masamiaoi.annotation.authIdentity;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author shenruiyan
 * @version 1.0
 * @Description: 文件入参
 * {RetentionPolicy.PARAMETER} 作用于入参方法上
 * @date 2023/2/23 - 10:08
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface FileParam {
    /**
     * 文件后缀
     */
    public String[] suffix() default {"doc", "xls", "ppt", "png", "txt"};

    /**
     * 文件大小 bate
     * 50M
     */
    public int size() default 52428800;

}
