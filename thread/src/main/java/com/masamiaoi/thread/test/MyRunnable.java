package com.masamiaoi.thread.test;

/**
 * @author: MASAMIAOI
 * @description: 线程方式 - Runnable
 * @date: 2023/4/1 15:09
 * @version: 1.0.0
 */
public class MyRunnable implements Runnable {

    int num = 10;

    @Override
    public void run() {
        while (num > 0) {
            System.out.println(Thread.currentThread().getName() + "......" + num--);
        }
    }

    public static void main(String[] args) {
        Runnable r = new MyRunnable();
        Thread t = new Thread(r);
        t.start();
    }
}
