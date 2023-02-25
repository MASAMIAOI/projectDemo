package com.masamiaoi.aop.service.impl;

import com.masamiaoi.aop.service.LogAopService;
import com.masamiaoi.aop.type.RestResultVo;
import org.springframework.stereotype.Service;

/**
 * @author shenruiyan
 * @version 1.0
 * @Description: 日志aop-serviceImpl
 * @date 2023/2/24 - 13:12
 */
@Service
public class LogAopServiceImpl implements LogAopService {

    /**
     * 日志插入测试
     *
     * @param tel 手机号
     */
    @Override
    public RestResultVo insertLog(String tel) {
        return RestResultVo.ok();
    }
}
