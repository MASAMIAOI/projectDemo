package com.masamiaoi.aop.type;

import lombok.Getter;

/**
 * @author shenruiyan
 * @version 1.0
 * @Description: 操作单元
 * @date 2023/2/24 - 11:48
 */
@Getter
public enum OperationUnitType {
    UNKNOWN("unknown"),
    USER("user"),
    EMPLOYEE("employee"),
    TEST("test"),
    Redis("redis");

    private String value;

    OperationUnitType() {
    }

    OperationUnitType(String value) {
        this.value = value;
    }
}
