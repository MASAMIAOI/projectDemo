package com.masamiaoi.designpattern.proxyTest.staticTest;

/**
 * @author: MASAMIAOI
 * @description: 婚庆公司
 * @date: 2023/3/28 23:49
 * @version: 1.0.0
 */
public class WeddingCompany implements Mary {

    //声明一个接口Mary类型变量
    private Mary mary;

    public WeddingCompany(Mary mary) {
        //形式参数是一个接口,需要接口实现类对象
        this.mary = mary;
    }

    @Override
    public void mary() {
        //代理角色需要帮助真实角色完成一件 "结婚这件事情"
        System.out.println("结婚之前,需要布置婚礼现场....");
        mary.mary();
        System.out.println("结婚之后,给婚庆公司付尾款....");
    }
}
