package com.masamiaoi.redis.type;


/**
 * @author shenruiyan
 * @version 1.0
 * @Description: 统一返回VO
 * @date 2022/3/10 - 13:48
 */
public class RestResultVo<T> {
    private String statusCode;
    private String statusMessage;
    private T content;
    private long count;

    public RestResultVo() {
    }

    public boolean success() {
        return RestResultCommonCode.SUCCESS.getCode().equalsIgnoreCase(this.statusCode);
    }

    public boolean error() {
        return !this.success();
    }

    public static <T> RestResultVo<T> ok() {
        RestResultVo vo = new RestResultVo();
        vo.setContent("");
        vo.setStatusCode(RestResultCommonCode.SUCCESS.getCode());
        vo.setStatusMessage(RestResultCommonCode.SUCCESS.getMessage());
        return vo;
    }

    public static <T> RestResultVo<T> success(T content) {
        RestResultVo vo = new RestResultVo();
        vo.setStatusCode(RestResultCommonCode.SUCCESS.getCode());
        vo.setStatusMessage(RestResultCommonCode.SUCCESS.getMessage());
        vo.setContent(content);
        return vo;
    }

    public static <T> RestResultVo<T> success(T content, long count) {
        RestResultVo vo = new RestResultVo();
        vo.setStatusCode(RestResultCommonCode.SUCCESS.getCode());
        vo.setStatusMessage(RestResultCommonCode.SUCCESS.getMessage());
        vo.setContent(content);
        vo.setCount(count);
        return vo;
    }

    public static <T> RestResultVo<T> error(RestResultCode code, String msg) {
        RestResultVo vo = new RestResultVo();
        vo.setStatusCode(code.getCode());
        vo.setStatusMessage(msg);
        return vo;
    }

    public static <T> RestResultVo<T> error(RestResultCode code) {
        RestResultVo vo = new RestResultVo();
        vo.setStatusCode(code.getCode());
        vo.setStatusMessage(code.getMessage());
        return vo;
    }

    public static <T> RestResultVo<T> errorFormat(RestResultCode code, Object... params) {
        return error(code, String.format(code.getMessage(), params));
    }

    public static <T> RestResultVo<T> errorFormat(RestResultCode code, String msg, Object... params) {
        return error(code, String.format(msg, params));
    }

    public String getStatusCode() {
        return this.statusCode;
    }

    public String getStatusMessage() {
        return this.statusMessage;
    }

    public T getContent() {
        return this.content;
    }

    public long getCount() {
        return this.count;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }

    public void setContent(T content) {
        this.content = content;
    }

    public void setCount(long count) {
        this.count = count;
    }
}

