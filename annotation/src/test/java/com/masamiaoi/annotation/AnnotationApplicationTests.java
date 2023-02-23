package com.masamiaoi.annotation;

import com.google.common.util.concurrent.RateLimiter;
import com.masamiaoi.annotation.vo.PeopleVO;
import com.masamiaoi.annotation.vo.ZhangsanVO;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.lang.annotation.Annotation;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

@SpringBootTest
class AnnotationApplicationTests {

    /**
     * 测试 @Inherited 注解作用
     *
     * @Inherited 表示该注解内容允许被集成
     */
    @Test
    void contextLoads() {
        Annotation[] PeopleAnnotations = PeopleVO.class.getAnnotations();
        Arrays.stream(PeopleAnnotations).forEach(System.out::println);
        System.out.println("----------------------------------");
        Annotation[] ZhansanAnnotations = ZhangsanVO.class.getAnnotations();
        Arrays.stream(ZhansanAnnotations).forEach(System.out::println);
    }

    @Test
    void rateLimiterTest() {
        String start = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        RateLimiter limiter = RateLimiter.create(1.0); // 这里的1表示每秒允许处理的量为1个
        for (int i = 1; i <= 10; i++) {
            double waitTime = limiter.acquire(i);// 请求RateLimiter, 超过permits会被阻塞
            System.out.println("cutTime=" + System.currentTimeMillis() + " call execute:" + i + " waitTime:" + waitTime);
        }
        String end = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        System.out.println("start time:" + start);
        System.out.println("end time:" + end);
    }


    /*
        smoothWarmingUp: 预热进行限流。 使用需要有准备工作
            RateLimiter.create(double permitsPerSecond,long warnupPeriod,TimeUnit unit)
        SmoothBursty： 存储限流
            RateLimiter.create(double permitsPerSecond)
     */
    @Test
    void reteLiterTest2() {
        //RateLimiter limiter = RateLimiter.create(10, 2, TimeUnit.SECONDS);//QPS 100
        RateLimiter limiter = RateLimiter.create(10);
        long start = System.currentTimeMillis();
        for (int i = 0; i < 30; i++) {
            double time = limiter.acquire();
            long after = System.currentTimeMillis() - start;
            if (time > 0D) {
                System.out.println(i + ",limited,等待:" + time + "，已开始" + after + "毫秒");
            } else {
                System.out.println(i + ",enough" + "，已开始" + after + "毫秒");
            }
            //模拟冷却时间，下一次loop可以认为是 bursty 开始
            if (i == 9) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        System.out.println("total time：" + (System.currentTimeMillis() - start));

    }

}
