package com.masamiaoi.thread.test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author: MASAMIAOI
 * @description: 线程池实现方式
 * @date: 2023/4/1 15:34
 * @version: 1.0.0
 */
public class ThreadPool {

    static class NumberThread implements Runnable {
        @Override
        public void run() {
            //遍历100以内的偶数
            for (int i = 0; i <= 100; i++) {
                if (i % 2 == 0) {
                    System.out.println(Thread.currentThread().getName() + ": " + i);
                }
            }
        }
    }

    static class NumberThread1 implements Runnable {
        @Override
        public void run() {
            //遍历100以内的奇数
            for (int i = 0; i <= 100; i++) {
                if (i % 2 != 0) {
                    System.out.println(Thread.currentThread().getName() + ": " + i);
                }
            }
        }
    }

    public static void main(String[] args) {
        //1. 提供指定线程数量的线程池
        ExecutorService service = Executors.newFixedThreadPool(10);

        //输出class java.util.concurrent.ThreadPoolExecutor
        System.out.println(service.getClass());

        ThreadPoolExecutor service1 = (ThreadPoolExecutor) service;
        //自定义线程池的属性
//        service1.setCorePoolSize(15);
//        service1.setKeepAliveTime();

        //2. 执行指定的线程的操作。需要提供实现Runnable接口或Callable接口实现类的对象
        service.execute(new NumberThread());//适用于Runnable
        service.execute(new NumberThread1());//适用于Runnable
//        service.submit(Callable callable);//适合使用于Callable

        //3. 关闭连接池
        service.shutdown();
    }
}
