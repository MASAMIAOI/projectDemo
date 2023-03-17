package com.masamiaoi.email.rest;

import com.masamiaoi.email.service.MailService;
import com.masamiaoi.email.service.impl.MailServiceSend;
import com.masamiaoi.email.service.impl.MailServiceSendByThy;
import com.masamiaoi.email.type.RestResultVo;
import com.masamiaoi.email.vo.MailVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.context.Context;

/**
 * @author: MASAMIAOI
 * @description: 邮件发送
 * @date: 2023/3/17 15:55
 * @version: 1.0.0
 */
@RestController
public class MailRest {

    /**
     * 邮件service
     */
    @Autowired
    MailService mailService;


    /**
     * 发送邮件
     */
    @PostMapping("/mail/send")
    public RestResultVo<Void> sendMail(MailVO mailVo, MultipartFile[] files) {
        mailVo.setMultipartFiles(files);
        //发送邮件和附件
        return mailService.sendMail(mailVo);
    }

    /**
     * 发送邮件
     */
    @PostMapping("/mail/sendPostMan")
    public RestResultVo<Void> sendPostMan(@RequestBody MailVO mailVo, MultipartFile[] files) {
        mailVo.setMultipartFiles(files);
        //发送邮件和附件
        return mailService.sendMail(mailVo);
    }


    /*---------------------------------------------------------------分割线-----------------------------------------------------------------------------------*/

    /**
     * 邮件发送
     */
    @Autowired
    MailServiceSend mailServiceSend;


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
        mailServiceSend.sendTextMailMessage(to, subject, text);
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
        mailServiceSend.sendHtmlMailMessage(to, subject, content);
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
        mailServiceSend.sendAttachmentMailMessage(to, subject, content, filePath);
        return RestResultVo.ok();
    }


    /*---------------------------------------------------------------分割线-----------------------------------------------------------------------------------*/


    @Autowired
    private MailServiceSendByThy mailServiceSendByThy;

    /**
     * 发送简单邮件
     */
    @GetMapping("/sendSimpleMail")
    public RestResultVo sendSimpleMail() {
        return mailServiceSendByThy.sendSimpleMail
                (
                        "ruiyan_shen@163.com",
                        "756907697@qq.com",
                        "欢迎测试",
                        "正文"
                );
    }

    /**
     * 发送复杂邮件（文本+图片+附件）
     */
    @GetMapping("/sendMimeMail")
    public RestResultVo<Void> sendMimeMail() {
        return mailServiceSendByThy.sendMimeMail
                (

                        "ruiyan_shen@163.com",
                        "756907697@qq.com",
                        "欢迎测试",
                        "<h3>~~~~~~~~~~~~~~~~</h3><br>" +
                                "<img src='cid:logo'>"
                );
    }

    /**
     * 发送模板邮件
     *
     * @param
     * @return
     */
    @GetMapping("/sendTemplateMail")
    public RestResultVo<Void> sendTemplateMail() {
        Context context = new Context();
        context.setVariable("username", "masamiaoi");
        return mailServiceSendByThy.sendTemplateMail
                (
                        "ruiyan_shen@163.com",
                        "756907697@qq.com",
                        "欢迎测试",
                        context
                );
    }

}
