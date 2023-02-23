package com.masamiaoi.annotation.authIdentity;

import java.lang.annotation.*;

/**
 * @author admin
 * @interface定义一个注解类
 * @Target也是用来修饰注解的元注解 注解类型 ElementType.METHOD 可以生效在方法上 。  ElementType.TYPE 可以生效在类上
 * Retention注解有一个属性value，是RetentionPolicy类型的，Enum RetentionPolicy是一个枚举类型，
 * 这个枚举决定了Retention注解应该如何去保持，也可理解为Rentention 搭配 RententionPolicy使用。RetentionPolicy有3个值：CLASS  RUNTIME   SOURCE
 * 用@Retention(RetentionPolicy.CLASS)修饰的注解，表示注解的信息被保留在class文件(字节码文件)中当程序编译时，但不会被虚拟机读取在运行的时候；
 * 用@Retention(RetentionPolicy.SOURCE )修饰的注解,表示注解的信息会被编译器抛弃，不会留在class文件中，注解的信息只会留在源文件中；
 * 用@Retention(RetentionPolicy.RUNTIME )修饰的注解，表示注解的信息被保留在class文件(字节码文件)中当程序编译时，会被虚拟机保留在运行时，
 * 所以他们可以用反射的方式读取。RetentionPolicy.RUNTIME 可以让你从JVM中读取Annotation注解的信息，以便在分析程序的时候使用.
 * @Documented 注解表明这个注解应该被 javadoc工具记录. 默认情况下,javadoc是不包括注解的. 但如果声明注解时指定了 @Documented,则它会被 javadoc 之类的工具处理,
 * 所以注解类型信息也会被包括在生成的文档中，是一个标记注解
 * @Inherited 标识该注解是否允许被子类集成
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface AuthIdentity {
    /**
     * value 说明
     * 0 应聘者  1 招聘者
     */
    int value() default 0;
}
