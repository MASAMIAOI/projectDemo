package com.masamiaoi.designpattern.proxyTest.staticTest;

/**
 * @author: MASAMIAOI
 * @description: 静态代理
 * @date: 2023/3/28 23:47
 * @version: 1.0.0
 */
public class Proxy {

    public static void main(String[] args) {

        //没有使用静态代理之前
        //接口多态或者是具体类new 具体类
        Mary mary = new You();
        mary.mary();
        System.out.println("-----------------------------------------");
        //使用静态代理
        You you = new You();
        //创建代理WeddingCompany角色
        WeddingCompany weddingCompany = new WeddingCompany(you);
        weddingCompany.mary();
    }
}
