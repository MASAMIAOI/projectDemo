package com.masamiaoi.mybatisplusdemo.demo;

/**
 * @author: MASAMIAOI
 * @description: TODO
 * @date: 2023/3/15 15:55
 * @version: 1.0.0
 */
public class Test {

    public static void main(String[] args) {
        Test test = new Test();
        test.test(10);
        OutClass outClass = new OutClass();
        outClass.outPrint(10);
    }

    //局部final变量a,b
    public void test(final int b) {//jdk8在这⾥做了优化, 不⽤写,语法糖，但实际上也是有的，也不能修改
        final int a = 10;
        //匿名内部类
        new Thread() {
            public void run() {
                System.out.println(a);
                System.out.println(b);
            }

            ;
        }.start();
    }


}

class OutClass {
    private final int age = 12;

    public void outPrint(final int x) {
        class InClass {
            public void InPrint() {
                System.out.println(x);
                System.out.println(age);
            }
        }
        new InClass().InPrint();
    }
}
