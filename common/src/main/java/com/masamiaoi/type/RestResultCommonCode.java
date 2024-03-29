package com.masamiaoi.type;

/**
 * @author shenruiyan
 * @version 1.0
 * @Description: 返回消息
 * @date 2022/3/10 - 13:50
 */
public enum RestResultCommonCode implements RestResultCode {
    SUCCESS("API-COMMON-INF-OK", "成功"),
    ERROR("API-COMMON-ERR-ERROR", "未知错误"),
    X_ERROR("API-COMMON-ERR-X_ERROR", "错了错了"),
    FAIL("API-COMMON-ERR-FAIL", "调用方法失败");

    public final String code;
    public final String message;

    private RestResultCommonCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return this.code;
    }

    public String getMessage() {
        return this.message;
    }
}
