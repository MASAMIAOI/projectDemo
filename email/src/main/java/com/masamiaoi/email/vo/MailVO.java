package com.masamiaoi.email.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

/**
 * @author: MASAMIAOI
 * @description: 邮件vo
 * @date: 2023/3/17 15:38
 * @version: 1.0.0
 */
@Data
public class MailVO {
    private String id;//邮件id
    private String from;//邮件发送人
    private String to;//邮件接收人（多个邮箱则用逗号","隔开）
    private String subject;//邮件主题
    private String text;//邮件内容
    private LocalDateTime sentDate;//发送时间
    private String cc;//抄送（多个邮箱则用逗号","隔开）
    private String bcc;//密送（多个邮箱则用逗号","隔开）
    private String status;//状态
    /**
     * JsonIgnore: 在json序列化时将java bean中的一些属性忽略掉，序列化和反序列化都受影响
     * 邮件附件
     */
    @JsonIgnore
    private MultipartFile[] multipartFiles;
}
