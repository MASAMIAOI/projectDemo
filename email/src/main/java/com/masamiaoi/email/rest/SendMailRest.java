package com.masamiaoi.email.rest;

import com.masamiaoi.email.service.impl.MailServiceSend;
import com.masamiaoi.email.type.RestResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: MASAMIAOI
 * @description: 邮箱发送
 * @date: 2023/3/17 16:33
 * @version: 1.0.0
 */
@RestController
@RequestMapping("/mail")
public class SendMailRest {

    /**
     * 邮件发送
     */
    @Autowired
    MailServiceSend mailService;


    /**
     * 发送文本邮件
     *
     * @param to      发送给谁
     * @param subject 正题
     * @param text    文本
     */
    @RequestMapping("/sendTextMail")
    public RestResultVo sendTextMail(String to, String subject, String text) {
        // TODO 记得检查参数是否为空， 这里不做示例
        mailService.sendTextMailMessage(to, subject, text);
        return RestResultVo.ok();
    }

    /**
     * 发送HTML邮件
     *
     * @param to      发送给谁
     * @param subject 正题
     * @param content 内容
     */
    @RequestMapping("/sendHtmlMailMessage")
    public RestResultVo sendHtmlMailMessage(String to, String subject, String content) {
        // TODO 记得检查参数是否为空， 这里不做示例
        mailService.sendHtmlMailMessage(to, subject, content);
        return RestResultVo.ok();
    }

    /**
     * 发送带附件的邮件
     *
     * @param to      发送给谁
     * @param subject 正题
     * @param content 内容
     */
    @RequestMapping("/sendAttachmentMailMessage")
    public RestResultVo sendAttachmentMailMessage(String to, String subject, String content, String filePath) {
        // TODO 记得检查参数是否为空， 这里不做示例
        filePath = "F:\\ms\\ms\\javaproject\\pdfTest\\1.jpg";
        content = StringUtils.isEmpty(content) ? "content" : content;
        mailService.sendAttachmentMailMessage(to, subject, content, filePath);
        return RestResultVo.ok();
    }
}
