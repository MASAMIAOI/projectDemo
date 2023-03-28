package com.masamiaoi.designpattern.proxyTest.staticTest;

/**
 * @author: MASAMIAOI
 * @description: 你
 * @date: 2023/3/28 23:48
 * @version: 1.0.0
 */
public class You implements Mary {

    /**
     * 结婚
     */
    @Override
    public void mary() {
        System.out.println("要结婚了,很开心...");
    }
}
