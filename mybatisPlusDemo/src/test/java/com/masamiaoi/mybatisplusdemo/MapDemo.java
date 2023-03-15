package com.masamiaoi.mybatisplusdemo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Hashtable;

/**
 * @author: MASAMIAOI
 * @description: map demo
 * @date: 2023/3/15 16:49
 * @version: 1.0.0
 */
@SpringBootTest
public class MapDemo {

    @Test
    public void mapTest() {
        HashMap<String, String> map = new HashMap<String, String>(3);
        
        Hashtable<String, String> hashtable = new Hashtable<String, String>(3);
    }

}
