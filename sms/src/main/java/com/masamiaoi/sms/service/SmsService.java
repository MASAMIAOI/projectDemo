package com.masamiaoi.sms.service;

import com.masamiaoi.sms.type.RestResultVo;

/**
 * @author: MASAMIAOI
 * @description: 短信发送
 * @date: 2023/3/18 1:53
 * @version: 1.0.0
 */
public interface SmsService {

    /**
     * 短信发送
     */
    RestResultVo<String> sendSms();
}
