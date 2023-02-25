package com.masamiaoi.aop.vo;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author shenruiyan
 * @version 1.0
 * @Description: 日志记录VO
 * @date 2023/2/24 - 11:55
 */
@Data
public class OperationLogVO {

    /**
     * id
     */
    private String id;

    /**
     * 创建日期
     */
    private LocalDateTime createTime;
    /**
     * 日志等级
     */
    private Integer level;
    /**
     * 被操作的对象
     */
    private String operationUnit;
    /**
     * 方法名
     */
    private String method;
    /**
     * 参数
     */
    private String args;
    /**
     * 操作人id
     */
    private String userId;
    /**
     * 操作人
     */
    private String userName;
    /**
     * 日志描述
     */
    private String describe;
    /**
     * 操作类型
     */
    private String operationType;
    /**
     * 方法运行时间
     */
    private Long runTime;
    /**
     * 方法返回值
     */
    private String returnValue;

}
