package com.masamiaoi.thread.test;

/**
 * @author: MASAMIAOI
 * @description: 匿名内部类测试
 * @date: 2023/4/1 15:39
 * @version: 1.0.0
 */
public class AnonymityTest {

    public static void main(String[] args) {

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                // 线程需要执行的任务代码
                System.out.println("子线程开始启动....");
                for (int i = 0; i < 30; i++) {
                    System.out.println("run i:" + i);
                }
            }
        });
        thread.start();
        

        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "\t上完自习，离开教室");
        }, "MyThread").start();
    }
}
