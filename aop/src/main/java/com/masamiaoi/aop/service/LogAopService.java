package com.masamiaoi.aop.service;

import com.masamiaoi.aop.type.RestResultVo;

/**
 * @author shenruiyan
 * @version 1.0
 * @Description: 日志aop-service
 * @date 2023/2/24 - 13:12
 */
public interface LogAopService {

    /**
     * 日志插入测试
     *
     * @param tel 手机号
     */
    RestResultVo insertLog(String tel);
}
