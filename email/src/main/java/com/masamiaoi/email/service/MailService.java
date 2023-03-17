package com.masamiaoi.email.service;

import com.masamiaoi.email.type.RestResultVo;
import com.masamiaoi.email.vo.MailVO;

/**
 * @author: MASAMIAOI
 * @description: 邮件发送
 * @date: 2023/3/17 15:40
 * @version: 1.0.0
 */
public interface MailService {

    /**
     * 发送邮件
     */
    RestResultVo<Void> sendMail(MailVO MailVO);

    /**
     * 获取邮箱发送者
     */
    String getMailSendFrom();
}
