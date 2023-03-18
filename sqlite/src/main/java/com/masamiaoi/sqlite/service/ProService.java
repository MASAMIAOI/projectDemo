package com.masamiaoi.sqlite.service;

import com.masamiaoi.sqlite.po.ProPO;
import com.masamiaoi.sqlite.type.RestResultVo;

import java.util.List;

/**
 * @author: MASAMIAOI
 * @description: pro -service
 * @date: 2023/3/18 19:57
 * @version: 1.0.0
 */
public interface ProService {
    /**
     * 查询全部
     */
    RestResultVo<List<ProPO>> queryAll();


    /**
     * 添加数据
     */
    RestResultVo<Integer> add(ProPO po);
}
