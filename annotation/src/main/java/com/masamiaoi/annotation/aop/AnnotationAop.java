package com.masamiaoi.annotation.aop;

import com.masamiaoi.annotation.authIdentity.AuthIdentity;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;


/**
 * @author shenruiyan
 * @version 1.0
 * @Description: 枚举测试切面类
 * @date 2023/2/22 - 15:09
 */
@Slf4j
@Aspect
@Component
public class AnnotationAop {

    /*
        匹配public方法
        execution(public * *(..))
        // 匹配名称以set开头的方法
        execution(* set*(..))
        // 匹配AccountService接口或类的方法
        execution(* com.xyz.service.AccountService.*(..))
        // 匹配service包及其子包的类或接口
    */


    /**
     * 定义切点Pointcut
     */
    @Pointcut("execution(* com.masamiaoi.annotation.rest.AnnotationRest.*(..))")
    public void executionService() {
    }


    /**
     * 环绕通知
     *
     * @throws Throwable 满足 executionService 和 authIdentity时会触发执行环绕通知
     * @annotation 注解声明在类上无效
     * @target 注解声明在方法上无效
     */
    @Around("executionService() && @annotation(authIdentity)")
    public Object doAround(ProceedingJoinPoint pjp, AuthIdentity authIdentity) throws Throwable {
        Object ret = pjp.proceed();
        int value = authIdentity.value();
        if (0 == value) {
            System.out.println("切面到 authIdentity 0");
        } else if (1 == value) {
            System.out.println("切面到 authIdentity 1");
        }
        return ret;
    }


    /**
     * 后置通知 log打印
     *
     * @param joinPoint
     */
    @After("executionService()")
    public void afterMethod(JoinPoint joinPoint) {
        //log.info("afterMethod annotationAop ...");
    }

    /**
     * 前置通知
     *
     * @param joinPoint
     */
    @Before("executionService()")
    public void beforeMethod(JoinPoint joinPoint) {
        //log.info("beforeMethod annotationAop ...");
    }

    /**
     * 返回通知
     *
     * @param joinpoint
     * @param rvt
     */
    @AfterReturning(pointcut = "executionService()", returning = "rvt")
    public void afterReturningMethod(JoinPoint joinpoint, Object rvt) {
        //log.info("afterReturningMethod annotationAop ...");
    }

    /**
     * 异常通知
     *
     * @param joinPoint
     */
    @AfterThrowing("executionService()")
    public void afterThrowingMethod(JoinPoint joinPoint) {
        //log.info("afterThrowingMethod annotationAop ...");
    }

}
