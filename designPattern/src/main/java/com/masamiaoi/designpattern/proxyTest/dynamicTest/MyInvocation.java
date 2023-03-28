package com.masamiaoi.designpattern.proxyTest.dynamicTest;

import org.springframework.cglib.proxy.InvocationHandler;

import java.lang.reflect.Method;

/**
 * @author: MASAMIAOI
 * @description: 动态代理
 * @date: 2023/3/29 0:04
 * @version: 1.0.0
 */
public class MyInvocation implements InvocationHandler {

    /**
     * 要针对谁产生代理: ud UserDao ud = new UserDaoImpl() ;
     * 因为代理角色可以是任意Java类型,所以用Object
     */
    private final Object target;

    public MyInvocation(Object target) {

        this.target = target;
    }

    //方法的返回值就是代理对象
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        System.out.println("权限校验");
        //调用接口的列表自己的方法:update(),delete(),add(),select()
        //当前实例--->真实角色
        //代理角色产生
        Object obj = method.invoke(target, args);
        System.out.println("产生日志文件");
        return obj;
    }
}