package com.masamiaoi.designpattern.proxyTest.dynamicTest;

import org.springframework.cglib.proxy.Proxy;

/**
 * @author: MASAMIAOI
 * @description: jdk 动态代理
 * @date: 2023/3/29 0:05
 * @version: 1.0.0
 */
public class JdkProxyDemo {

    public static void main(String[] args) {

        //接口多态:测试UserDao
        UserDao ud = new UserDaoImpl(); //真实角色
        //版本1:现在需要对功能增强,这个代码不合适!
        ud.add();
        ud.update();
        ud.select();
        ud.delete();
        System.out.println("----------------------------------");
        UserDao ud2 = new UserDaoImpl2();
        ud2.add();
        ud2.update();
        ud2.select();
        ud2.delete();
        System.out.println("----------------Jdk动态代理------------------");
        //前提示需要一个接口:UserDao
        MyInvocation handler = new MyInvocation(ud);
        // public static Object newProxyInstance(ClassLoader loader,Class<?>[] interfaces,InvocationHandler h)
        UserDao proxyInstance = (UserDao) Proxy.newProxyInstance(
                ud.getClass().getClassLoader(),
                ud.getClass().getInterfaces(),
                handler);
        proxyInstance.add();
        proxyInstance.update();
        proxyInstance.select();
        proxyInstance.delete();
        System.out.println("----------------Jdk动态代理2222------------------");
        //前提示需要一个接口:UserDao
        MyInvocation handler2 = new MyInvocation(ud2);
        // public static Object newProxyInstance(ClassLoader loader,Class<?>[] interfaces,InvocationHandler h)
        UserDao proxyInstance2 = (UserDao) Proxy.newProxyInstance(
                ud.getClass().getClassLoader(),
                ud.getClass().getInterfaces(),
                handler2);
        proxyInstance2.add();
        proxyInstance2.update();
        proxyInstance2.select();
        proxyInstance2.delete();
    }
}