package com.masamiaoi.file.test;

import java.util.*;

/**
 * @author: MASAMIAOI
 * @description: 测试类
 * @date: 2023/4/2 19:41
 * @version: 1.0.0
 */
public class Test {


    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        Map<String, String> map = new HashMap<>();
        Map<String, String> table = new Hashtable<>();
        list.add("123");
        list.add("444");
        list.add("555");

        map.put("123", "123");
        map.put("444", null);
        map.put("555", "555");
        map.put(null, null);

        table.put("123", "123");
        table.put("13", "1111");
        System.out.println(list.get(0));
        list.forEach(v -> System.out.println(v));
        map.forEach((k, v) -> System.out.println(k + ":" + v));


        List<String> list1 = Collections.singletonList("a");

        change(list1);
        System.out.println(list1);
    }


    public static void change(List<String> list) {
        list = Collections.singletonList("b");

        System.out.println(list);
    }
}
