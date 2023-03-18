package com.masamiaoi.sqlite.service.impl;

import com.masamiaoi.sqlite.dao.ProMapper;
import com.masamiaoi.sqlite.po.ProPO;
import com.masamiaoi.sqlite.service.ProService;
import com.masamiaoi.sqlite.type.RestResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: MASAMIAOI
 * @description: pro - service
 * @date: 2023/3/18 19:57
 * @version: 1.0.0
 */
@Service
public class ProServiceImpl implements ProService {

    /**
     * proService
     */
    @Autowired
    ProMapper proMapper;


    /**
     * 查询全部
     */
    @Override
    public RestResultVo<List<ProPO>> queryAll() {
        return RestResultVo.success(proMapper.list());
    }

    /**
     * 添加
     */
    @Override
    public RestResultVo<Integer> add(ProPO po) {
        return RestResultVo.success(proMapper.add(po));
    }
}
