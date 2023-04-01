package com.masamiaoi.thread.test;

/**
 * @author: MASAMIAOI
 * @description: 线程模拟方式 - Thread
 * @date: 2023/4/1 14:36
 * @version: 1.0.0
 */
public class MyThread extends Thread {

    public MyThread(String name) {
        super(null, null, name);
    }


    int number = 10;


    @Override
    public void run() {
        while (number > 0) {
            System.out.println(Thread.currentThread().getName() + "......" + number--);
        }
    }

    public static void main(String[] args) {
        MyThread mt = new MyThread("test");
        mt.start();
    }
}
