package com.masamiaoi.annotation.authIdentity;

import java.lang.annotation.*;

/**
 * @author shenruiyan
 * @version 1.0
 * @Description: 测试继承注解
 * @date 2023/2/22 - 14:46
 */
@Target(ElementType.TYPE) // 作用于类上
@Retention(RetentionPolicy.RUNTIME) // 作用于代码运行中
@Inherited
public @interface HasInherited {
}
