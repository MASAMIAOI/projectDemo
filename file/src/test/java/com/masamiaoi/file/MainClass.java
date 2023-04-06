package com.masamiaoi.file;

import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author: MASAMIAOI
 * @description: 斐波那契数列
 * @date: 2023/4/6 20:45
 * @version: 1.0.0
 */
@SpringBootTest
public class MainClass {

    /*
        有一列数:0.1...从第三项开始其值为前面所有项值之和，求第 n 项值，用递归算法 实现
        0, 1, 1, 2, 4, 8, 16, 32, 64, 128
        根据规则发现，新的数字总是之前数字 * 2 | 类似 斐波那契数列
    */

    public static void main(String[] args) {
        for (int i = 0; i <= 10; i++) {
            System.out.println(fibonacci(i));
        }
    }

    /**
     * 递归求和
     */
    public static long fibonacci(long number) {
        if (number == 0 || number == 1)
            return number;
        else {
            long fibonacci = fibonacci(number - 1);
            return fibonacci * 2;
        }
    }

}
