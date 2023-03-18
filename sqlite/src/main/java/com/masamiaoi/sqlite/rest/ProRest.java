package com.masamiaoi.sqlite.rest;

import com.masamiaoi.sqlite.po.ProPO;
import com.masamiaoi.sqlite.service.ProService;
import com.masamiaoi.sqlite.type.RestResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author: MASAMIAOI
 * @description: pro测试
 * @date: 2023/3/18 19:54
 * @version: 1.0.0
 */
@RestController
public class ProRest {

    /**
     * pro -service
     */
    @Autowired
    ProService proService;

    /**
     * 查询全部
     */
    @RequestMapping(method = {RequestMethod.POST, RequestMethod.GET}, value = "/queryAll")
    public RestResultVo<List<ProPO>> queryAll() {
        return proService.queryAll();
    }


    /**
     * 添加对象
     */
    @PostMapping(value = "/add")
    public RestResultVo<Integer> add(@RequestBody ProPO po) {
        return proService.add(po);
    }
}
