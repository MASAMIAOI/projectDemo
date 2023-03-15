package com.masamiaoi.mybatisplusdemo.demo;

import com.masamiaoi.mybatisplusdemo.po.UserPO;

/**
 * @author: MASAMIAOI
 * @description: fina 测试基本类型和用用类型
 * @date: 2023/3/15 15:52
 * @version: 1.0.0
 */
public class FinalReferenceTest {
    public static void main() {
        final int[] iArr = {1, 2, 3, 4};
        iArr[2] = -3;//合法
//        iArr = null;//⾮法，对iArr不能重新赋值
        final UserPO p = new UserPO();
        p.setAge(24);//合法
//        p = null;//⾮法
    }
}
