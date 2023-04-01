package com.masamiaoi.thread.test;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

/**
 * @author: MASAMIAOI
 * @description: 线程带返回值的实现方式 - 等待执行完成获取结果
 * @date: 2023/4/1 15:27
 * @version: 1.0.0
 */
public class CallableTestFuture implements Callable {

    @Override
    public Object call() throws Exception {
        System.out.println("Callable come in");
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return 1024;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        //使用构造方法：FutureTask(Callable<V> callable)
        FutureTask<Integer> futureTask = new FutureTask<Integer>(new CallableTestFuture());

        new Thread(futureTask, "AAA").start();
        //new Thread(futureTask, "BBB").start();//复用，直接取值，不要重启两个线程
        //PS：多个线程来抢一个futureTask，里面的计算方法call()只计算一次，要想多次算，要创建多个FutureTask<V>对象

        int a = 100;
        int b = 0;

        //b = futureTask.get();//要求获得Callable线程的计算结果，如果没有计算完成就要去强求，会导致堵塞，直到计算完成
        while (!futureTask.isDone()) {//当futureTask完成后取值
            b = futureTask.get();
        }
        System.out.println("Result=" + (a + b));
    }

}
