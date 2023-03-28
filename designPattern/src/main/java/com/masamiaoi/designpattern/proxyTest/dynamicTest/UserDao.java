package com.masamiaoi.designpattern.proxyTest.dynamicTest;

/**
 * @author: MASAMIAOI
 * @description: 用户
 * @date: 2023/3/28 23:59
 * @version: 1.0.0
 */
public interface UserDao {
    /**
     * 添加功能
     */
    void add();

    /**
     * 修改功能
     */
    void update();

    /**
     * 查询功能
     */
    void select();

    /**
     * 删除功能
     */
    void delete();
}
