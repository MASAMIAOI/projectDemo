package com.masamiaoi.file;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author: MASAMIAOI
 * @description: TestBasic
 * @date: 2023/4/6 20:08
 * @version: 1.0.0
 */
@SpringBootTest
public class TestBasic {
    public void change(String s) {
        s = "change";
    }

    @Test
    public void testChange() {
        String s = "hello";
        change(s);
        System.out.println(s);
    }

    @Test
    public void testIplus() {
        int i = 1;
        System.out.println(i++);
        System.out.println(++i);
    }

    /**
     * 排序字符串， 按照个数， 然后大小
     */
    public static void main(String[] args) {
        List<String> list = Arrays.asList("FA", "WWA", "YKJ", "JASA");
        List<String> sortList = list.stream().sorted(
                Comparator.comparing(String::length)
                        .thenComparing(String::compareTo)
                        .reversed()
        ).collect(Collectors.toList());
        System.out.println(sortList);
    }
}
