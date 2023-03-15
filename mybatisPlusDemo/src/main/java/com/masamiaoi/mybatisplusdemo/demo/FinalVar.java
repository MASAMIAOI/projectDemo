package com.masamiaoi.mybatisplusdemo.demo;

/**
 * @author: MASAMIAOI
 * @description: fina 赋值测试
 * @date: 2023/3/15 15:48
 * @version: 1.0.0
 */
public class FinalVar {

    // 再声明的时候就需要赋值 或者静态代码块赋值
    final static int a = 1;

    /*   static {
        a = 2;
    }*/

    // 再声明的时候就需要赋值 或者代码块中赋值 或者构造器赋值
    final int b = 1;

    /*{
        b = 2;
    }*/

    public static void main(String[] args) {
        final int localA;
        localA = 1;
//        localA = 2; // 不允许再次赋值
    }


}
