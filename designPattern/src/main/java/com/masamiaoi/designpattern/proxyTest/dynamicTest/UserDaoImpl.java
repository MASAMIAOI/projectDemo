package com.masamiaoi.designpattern.proxyTest.dynamicTest;

/**
 * @author: MASAMIAOI
 * @description: 用户实现类
 * @date: 2023/3/29 0:02
 * @version: 1.0.0
 */
public class UserDaoImpl implements UserDao {

    @Override
    public void add() {

        System.out.println("添加功能");
    }

    @Override
    public void update() {

        System.out.println("修改功能");
    }

    @Override
    public void select() {

        System.out.println("查询功能");
    }

    @Override
    public void delete() {

        System.out.println("删除功能");
    }
}
