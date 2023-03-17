package com.masamiaoi.email.service.impl;

import com.masamiaoi.email.type.RestResultCommonCode;
import com.masamiaoi.email.type.RestResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

/**
 * @author: MASAMIAOI
 * @description: 邮件结合 thy 使用
 * @date: 2023/3/17 18:28
 * @version: 1.0.0
 */
@Service
public class MailServiceSendByThy {

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private TemplateEngine templateEngine;

    /**
     * 发送简单邮件
     *
     * @param from
     * @param to
     * @param subject
     * @param text
     */
    public RestResultVo<Void> sendSimpleMail(String from, String to, String subject, String text) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        // 发件人
        simpleMailMessage.setFrom(from);
        // 收件人
        simpleMailMessage.setTo(to);
        // 邮件主题
        simpleMailMessage.setSubject(subject);
        // 邮件内容
        simpleMailMessage.setText(text);
        javaMailSender.send(simpleMailMessage);
        System.out.println("发送邮件成功：" + from + "->" + to);
        return RestResultVo.ok();
    }

    /**
     * 发送复杂邮件
     *
     * @param from
     * @param to
     * @param subject
     * @param text
     * @return
     */
    public RestResultVo<Void> sendMimeMail(String from, String to, String subject, String text) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(subject);
            // 设置邮件内容，第二个参数设置是否支持 text/html 类型
            helper.setText(text, true);
            helper.addInline("logo", new ClassPathResource("img/logo.jpg"));
            helper.addAttachment("logo.pdf", new ClassPathResource("doc/logo.pdf"));
            javaMailSender.send(mimeMessage);
            System.out.println("发送邮件成功：" + from + "->" + to);
            return RestResultVo.ok();
        } catch (MessagingException e) {
            return RestResultVo.error(RestResultCommonCode.EMAIL_SEND_FAIL);
        }
    }

    /**
     * 发送模板邮件
     *
     * @param from
     * @param to
     * @param subject
     * @param context
     * @return
     */
    public RestResultVo<Void> sendTemplateMail(String from, String to, String subject, Context context) {
        MimeMessage message = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(subject);
            // 解析邮件模板
            String text = templateEngine.process("mailTemplate", context);
            helper.setText(text, true);
            javaMailSender.send(message);
            System.out.println("发送邮件成功：" + from + "->" + to);
            return RestResultVo.ok();
        } catch (Exception e) {
            return RestResultVo.error(RestResultCommonCode.EMAIL_SEND_FAIL);
        }
    }
}
