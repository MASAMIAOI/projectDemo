package com.masamiaoi.sms.util;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;

/**
 * @author: MASAMIAOI
 * @description: 短信发送工具类
 * @date: 2023/3/18 1:42
 * @version: 1.0.0
 */
public class SmsTool {
    /**
     * 产品名称:云通信短信API产品,开发者无需替换
     */
    static final String PRODUCT = "Dysmsapi";
    /**
     * 产品域名,开发者无需替换
     */
    static final String DOMAIN = "dysmsapi.aliyuncs.com";
    /**
     * AccessKey ID
     */
    static final String ACCESS_KEY_ID = "LTAIM5N776CAL0bC";
    /**
     * AccessKey Secret
     */
    static final String ACCESS_KEY_SECRET = "xxxxxx";

    /**
     * 邮件发送
     *
     * @param phone        手机号
     * @param signName     前面
     * @param valideCode   模板中的code
     * @param TemplateCode 短信模板 code
     */
    public static SendSmsResponse sendSms(String phone, String signName, String valideCode, String TemplateCode) throws ClientException {
        //可自助调整超时时间
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");
        //初始化acsClient,暂不支持region化
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", ACCESS_KEY_ID, ACCESS_KEY_SECRET);
        DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", PRODUCT, DOMAIN);
        IAcsClient acsClient = new DefaultAcsClient(profile);

        //组装请求对象-具体描述见控制台-文档部分内容
        SendSmsRequest request = new SendSmsRequest();
        //必填:待发送手机号
        request.setPhoneNumbers(phone);
        //必填:短信签名-可在短信控制台中找到
        request.setSignName(signName);
        //必填:短信模板-可在短信控制台中找到
        request.setTemplateCode(TemplateCode);
        //可选:模板中的变量替换JSON串,如模板内容为"您好，您的货物包裹到了，验证码是：${validecode},请在一天之内领取。谢谢。"时,此处的值为
        request.setTemplateParam(valideCode);

        //选填-上行短信扩展码(无特殊需求用户请忽略此字段)
//        request.setSmsUpExtendCode("90997");

        //可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
        //request.setOutId("yourOutId");

        return acsClient.getAcsResponse(request);
    }
}
