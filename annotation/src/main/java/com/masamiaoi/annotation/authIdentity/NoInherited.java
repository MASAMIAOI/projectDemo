package com.masamiaoi.annotation.authIdentity;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author shenruiyan
 * @version 1.0
 * @Description: 无继承注解
 * @date 2023/2/22 - 14:47
 */
@Target(ElementType.TYPE) // 作用于类上
@Retention(RetentionPolicy.RUNTIME) // 作用于代码运行中
public @interface NoInherited {

}
