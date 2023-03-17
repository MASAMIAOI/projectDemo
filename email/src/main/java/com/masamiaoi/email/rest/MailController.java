package com.masamiaoi.email.rest;

import com.masamiaoi.email.service.MailService;
import com.masamiaoi.email.type.RestResultVo;
import com.masamiaoi.email.vo.MailVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author: MASAMIAOI
 * @description: 邮件发送
 * @date: 2023/3/17 15:55
 * @version: 1.0.0
 */
@RestController
public class MailController {

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

}
