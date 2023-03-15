package com.masamiaoi.mybatisplusdemo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author: MASAMIAOI
 * @description: 其它测试类
 * @date: 2023/3/15 15:04
 * @version: 1.0.0
 */
@SpringBootTest
public class OtherTest {

    /**
     * == 和 equals 测试比较说明
     * ==：对⽐的是栈中的值，基本数据类型是变量值，引⽤类型是堆中内存对象的地址
     * equals：object中默认也是采⽤==⽐较，通常会重写
     */
    @Test
    public void demoTest() {
        String str1 = "test";
        String str2 = new String("test");
        String str3 = str2;
        System.out.println("------------------------------------");
        System.out.println(str1 == str2);
        System.out.println("------------------------------------");
        System.out.println(str1 == str3);
        System.out.println("------------------------------------");
        System.out.println(str2 == str3);
        System.out.println("------------------------------------");
        System.out.println(str1.equals(str3));
        System.out.println("------------------------------------");
        System.out.println(str1.equals(str2));
        System.out.println("------------------------------------");
        System.out.println(str2.equals(str3));
    }

    /**
     * final 相关使用
     */
    @Test
    public void FinalVar() {
        final int localA; //局部变量只声明没有初始化，不会报错,与final⽆关。
        localA = 0;//在使⽤之前⼀定要赋值
//        localA = 1;// 但是不允许第⼆次赋值
        System.out.println(localA);
    }

    /**
     * 匿名内部类测试
     */
    public static void main(String[] args) {
        com.masamiaoi.mybatisplusdemo.demo.Test test = new com.masamiaoi.mybatisplusdemo.demo.Test();
        test.test(10);
    }

}
