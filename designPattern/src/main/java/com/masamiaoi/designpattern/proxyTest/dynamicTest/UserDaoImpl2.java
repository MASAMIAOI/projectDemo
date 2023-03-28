package com.masamiaoi.designpattern.proxyTest.dynamicTest;

/**
 * @author: MASAMIAOI
 * @description: 用户实现类2
 * @date: 2023/3/29 0:02
 * @version: 1.0.0
 */
public class UserDaoImpl2 implements UserDao {

    @Override
    public void add() {

        System.out.println("权限校验");
        System.out.println("执行添加功能");
        System.out.println("产生日志文件");
    }

    @Override
    public void update() {

        System.out.println("权限校验");
        System.out.println("执行修改功能");
        System.out.println("产生日志文件");
    }

    @Override
    public void select() {

        System.out.println("权限校验");
        System.out.println("执行查询功能");
        System.out.println("产生日志文件");
    }

    @Override
    public void delete() {

        System.out.println("权限校验");
        System.out.println("执行删除功能");
        System.out.println("产生日志文件");
    }
}