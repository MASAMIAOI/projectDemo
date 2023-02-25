package com.masamiaoi.aop.type;

import lombok.Getter;

/**
 * @author shenruiyan
 * @version 1.0
 * @Description: 操作类型枚举
 * @date 2023/2/24 - 11:46
 */
@Getter
public enum OperationType {
    UNKNOWN("unknown"),
    DELETE("delete"),
    SELECT("select"),
    UPDATE("update"),
    INSERT("insert");

    private String value;

    OperationType(String s) {
        this.value = s;
    }
    
}
