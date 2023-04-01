package com.masamiaoi.thread.test;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author: MASAMIAOI
 * @description: 线程带返回值的实现方式
 * @date: 2023/4/1 15:12
 * @version: 1.0.0
 */
public class CallableTest implements Callable {

    @Override
    public Object call() throws Exception {
        int sum = 0;
        //把100以内的偶数相加
        for (int i = 1; i <= 100; i++) {
            if (i % 2 == 0) {
                System.out.println(i);
                sum += i;
            }
        }
        return sum;
    }

    public static void main(String[] args) {
        //创建Callable接口实现类的对象
        CallableTest numThread = new CallableTest();

        //将此Callable接口实现类的对象作为传递到FutureTask构造器中，创建FutureTask的对象
        FutureTask futureTask = new FutureTask(numThread);

        //将FutureTask的对象作为参数传递到Thread类的构造器中，创建Thread对象，并调用start()
        new Thread(futureTask).start();

        try {
            //获取Callable中call方法的返回值
            //get()返回值即为FutureTask构造器参数Callable实现类重写的call()的返回值。
            Object sum = futureTask.get();
            System.out.println("总和为：" + sum);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
            System.out.println("线程处理异常：" + e);
        }
    }


}
