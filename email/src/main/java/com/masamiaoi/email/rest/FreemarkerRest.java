package com.masamiaoi.email.rest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: MASAMIAOI
 * @description: TODO
 * @date: 2023/3/17 17:59
 * @version: 1.0.0
 */
@Controller
@RequestMapping(value = "/freemarker")
public class FreemarkerRest {


    @RequestMapping("/fm/index")
    public String fmIndex(ModelMap modelMap) {

        Map<String, String> map = new HashMap<>();
        map.put("name", "aoppp");
        map.put("desc", "描述");
        // 添加属性 给模版
        modelMap.addAttribute("user", map);
        return "fm/index";
    }

}
