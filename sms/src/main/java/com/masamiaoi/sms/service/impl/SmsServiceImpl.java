package com.masamiaoi.sms.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.masamiaoi.sms.service.SmsService;
import com.masamiaoi.sms.type.RestResultCommonCode;
import com.masamiaoi.sms.type.RestResultVo;
import com.masamiaoi.sms.util.CodeUtil;
import com.masamiaoi.sms.util.SmsTool;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author: MASAMIAOI
 * @description: 短信发送
 * @date: 2023/3/18 1:54
 * @version: 1.0.0
 */
@Slf4j
@Service
public class SmsServiceImpl implements SmsService {

    /**
     * 短信发送
     */
    @Override
    public RestResultVo<String> sendSms() {
        SendSmsResponse sendSmsResponse = null;
        try {
            JSONObject mes = new JSONObject();
            mes.put("validecode", CodeUtil.generateVerifyCode(6));
            sendSmsResponse = SmsTool.sendSms("15226988811", "青梅糖", mes.toJSONString(), "SMS_171565028");
        } catch (Exception ex) {
            log.error("send sms error: ", ex);
            return RestResultVo.error(RestResultCommonCode.SMS_SEND_FAIL);
        }
        if (null != sendSmsResponse && "OK".equals(sendSmsResponse.getCode())) {
            return RestResultVo.success("短信发送成功");
        } else {
            return RestResultVo.error(RestResultCommonCode.SMS_SEND_FAIL,
                    null != sendSmsResponse ? sendSmsResponse.getMessage() : RestResultCommonCode.SMS_SEND_FAIL.getMessage());
        }
    }
}
