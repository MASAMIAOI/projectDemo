package com.masamiaoi.aop.rest;

import com.masamiaoi.aop.authIdentity.OperationLogDetail;
import com.masamiaoi.aop.service.LogAopService;
import com.masamiaoi.aop.type.OperationType;
import com.masamiaoi.aop.type.OperationUnitType;
import com.masamiaoi.aop.type.RestResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author shenruiyan
 * @version 1.0
 * @Description: 日志aop-rest接口
 * @date 2023/2/24 - 13:11
 */
@RestController
@RequestMapping(path = "/logAop")
public class LogAopRest {

    @Autowired
    LogAopService logAopService;


    /**
     * 日志插入测试
     *
     * @param tel 手机号
     */
    @OperationLogDetail(detail = "通过手机号[{{tel}}]获取用户名", level = 9, operationUnit = OperationUnitType.TEST, operationType = OperationType.INSERT)
    @RequestMapping(method = RequestMethod.POST, path = "/insertLog")
    public RestResultVo insertLog(@RequestParam("tel") String tel) {
        return logAopService.insertLog(tel);
    }

}
