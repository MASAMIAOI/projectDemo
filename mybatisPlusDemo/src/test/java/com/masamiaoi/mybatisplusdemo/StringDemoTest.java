package com.masamiaoi.mybatisplusdemo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author: MASAMIAOI
 * @description: StringDemo 测试类
 * @date: 2023/3/15 16:07
 * @version: 1.0.0
 */
@SpringBootTest
public class StringDemoTest {

    @Test
    public void test() {
        String a = "123";
        a = "321";
        // buffer 线程安全
        StringBuffer buffer = new StringBuffer();
        buffer.append("123").append("321");
        // 线程不安全
        StringBuilder builder = new StringBuilder();
        builder.append("123").append("321");
        System.out.println(a);
        System.out.println(buffer);
        System.out.println(builder);
    }
}
