package com.masamiaoi.sms.rest;

import com.masamiaoi.sms.service.SmsService;
import com.masamiaoi.sms.type.RestResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: MASAMIAOI
 * @description: 短信rest
 * @date: 2023/3/17 19:34
 * @version: 1.0.0
 */
@RestController
@RequestMapping(value = "/sms")
public class SmsRest {


    @Autowired
    SmsService smsService;

    /**
     * 短信发送
     */
    @RequestMapping(method = RequestMethod.GET, value = "/sendSms")
    public RestResultVo<String> sendSms() {
        return smsService.sendSms();
    }


}
