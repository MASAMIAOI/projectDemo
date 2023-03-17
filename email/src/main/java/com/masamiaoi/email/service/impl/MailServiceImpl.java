package com.masamiaoi.email.service.impl;

import com.masamiaoi.email.service.MailService;
import com.masamiaoi.email.type.RestResultCommonCode;
import com.masamiaoi.email.type.RestResultVo;
import com.masamiaoi.email.util.TimeUtils;
import com.masamiaoi.email.vo.MailVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * @author: MASAMIAOI
 * @description: 邮件发送
 * @date: 2023/3/17 15:41
 * @version: 1.0.0
 */
@Slf4j
@Service
public class MailServiceImpl implements MailService {

    /**
     * 注入邮件工具类
     */
    @Autowired
    JavaMailSenderImpl mailSender;

    /**
     * 发送邮件
     */
    public RestResultVo<Void> sendMail(MailVO MailVO) {
        try {
            checkMail(MailVO);
            sendMimeMail(MailVO);
            return saveMail(MailVO);
        } catch (Exception e) {
            //打印错误信息
            log.error("发送邮件失败:", e);
            return RestResultVo.error(RestResultCommonCode.EMAIL_SEND_FAIL);
        }

    }

    /**
     * 检测邮件信息类
     */
    private void checkMail(MailVO MailVO) {
        if (StringUtils.isEmpty(MailVO.getTo())) {
            throw new RuntimeException("邮件收信人不能为空");
        }
        if (StringUtils.isEmpty(MailVO.getSubject())) {
            throw new RuntimeException("邮件主题不能为空");
        }
        if (StringUtils.isEmpty(MailVO.getText())) {
            throw new RuntimeException("邮件内容不能为空");
        }
    }

    /**
     * 构建复杂邮件信息类
     */
    private void sendMimeMail(MailVO MailVO) {
        try {
            //true表示支持复杂类型
            MimeMessageHelper messageHelper = new MimeMessageHelper(mailSender.createMimeMessage(), true);
            //邮件发信人从配置项读取
            MailVO.setFrom(getMailSendFrom());
            //邮件发信人
            messageHelper.setFrom(MailVO.getFrom());
            //邮件收信人
            messageHelper.setTo(MailVO.getTo().split(","));
            //邮件主题
            messageHelper.setSubject(MailVO.getSubject());
            //邮件内容
            messageHelper.setText(MailVO.getText());
            //抄送
            if (!StringUtils.isEmpty(MailVO.getCc())) {
                messageHelper.setCc(MailVO.getCc().split(","));
            }
            //密送
            if (!StringUtils.isEmpty(MailVO.getBcc())) {
                messageHelper.setCc(MailVO.getBcc().split(","));
            }
            //添加邮件附件
            if (MailVO.getMultipartFiles() != null) {
                for (MultipartFile multipartFile : MailVO.getMultipartFiles()) {
                    messageHelper.addAttachment(Objects.requireNonNull(multipartFile.getOriginalFilename()), multipartFile);
                }
            }
            //发送时间
            if (StringUtils.isEmpty(MailVO.getSentDate())) {
                MailVO.setSentDate(LocalDateTime.now());
                messageHelper.setSentDate(TimeUtils.localDateTimeToDate(MailVO.getSentDate()));
            }
            //正式发送邮件
            mailSender.send(messageHelper.getMimeMessage());
            MailVO.setStatus("ok");
            log.info("发送邮件成功：{} -> {}", MailVO.getFrom(), MailVO.getTo());
        } catch (Exception e) {
            log.error("邮件发送失败", e);
            //发送失败
            throw new RuntimeException(e);
        }
    }

    /**
     * 保存邮件
     */
    private RestResultVo<Void> saveMail(MailVO MailVO) {
        //将邮件保存到数据库..
        return RestResultVo.ok();
    }

    /**
     * 获取邮件发信人
     */
    public String getMailSendFrom() {
        return mailSender.getJavaMailProperties().getProperty("from");
    }

}
