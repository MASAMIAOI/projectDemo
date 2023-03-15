package com.masamiaoi.mybatisplusdemo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * @author: MASAMIAOI
 * @description: list 和 set demo
 * @date: 2023/3/15 16:28
 * @version: 1.0.0
 */
@SpringBootTest
public class ListAndSetDemo {


    /**
     * list和 set 测试类
     */
    @Test
    public void listAndSet() {
        ArrayList<String> list = new ArrayList<>(3);
        list.add(0, "23");
        list.add(1, "33");
        list.add(2, null);
        list.add("77777777777777777");
        System.out.println(list);


        HashSet<String> set = new HashSet<String>(3);
        set.add("12");
        set.add("23");
        set.add("23");
        set.add(null);
        System.out.println(set);


    }
}
