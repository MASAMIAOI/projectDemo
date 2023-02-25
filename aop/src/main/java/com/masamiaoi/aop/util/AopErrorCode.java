package com.masamiaoi.aop.util;

import lombok.Getter;

/**
 * @author shenruiyan
 * @version 1.0
 * @Description: 错误枚举
 * @date 2023/2/24 - 16:32
 */
@Getter
public enum AopErrorCode {
    /**
     * “OK”(成功)-请求已经成功。
     * 头部(Header)说明：无。
     * 消息体(Body)说明：在消息体中返回所请求的资源。
     */
    OK(200),
    /**
     * “Created”(创建成功)-请求已经创建了一项新的
     * 资源。
     * 头部(Header)说明：Location 头部字段包含了新创
     * 建资源的 URI。
     * 消息体(Body)说明：响应消息体返回一个描述新创
     * 建资源的实体。
     */
    Created(201),
    /**
     * “No Content”(无内容)-请求已经成功，但没有返
     * 回数据。
     * 头部(Header)说明：无。
     * 消息体(Body)说明：不允许有消息体。
     */
    NoContent(204),

    /**
     * “Moved Permanently”(被永久移动)-所请求资源
     * 位置已被永久移动。
     * 头部(Header)说明：Location 头部字段包含资源新
     * 位置的 URI。
     * 消息体(Body)说明：消息体可以包含资源的新位置。
     */
    MovedPermanently(301),

    /**
     * “Found”(找到)-所请求资源应该通过该位置访问，
     * 但实际上该资源位于另一个位置。这是设置别名的
     * 典型用法。
     * 头部(Header)说明：Location 头部字段包含资源的
     * URI。
     * 消息体(Body)说明：消息体可以包含资源的新位置。
     */
    Found(302),

    /**
     * “Bad Request”(坏请求)-请求消息构建不对，这
     * 通常用于创建或更新一个资源时，其数据是不完整
     * 或不正确的。
     * 头部(Header)说明：通过 HTTP 状态头部发送的原因
     * 说明(Reason-Phrase)包含错误信息。
     * 消息体(Body)说明：响应消息体可以包含更多的除
     * 头部“原因说明”以外的隐含错误信息。
     */
    BadRequest(400),

    /**
     * “Unauthorized”(未授权的)-请求需要用户认证后
     * 才能访问该资源。如果请求包含无效认证数据，则
     * 发送该响应消息。
     * 头部(Header)说明：在 WWW-Authenticate 头部字段
     * 中应指定至少一种认证机制。通过 HTTP 状态头部发
     * 送的原因说明(Reason-Phrase)可包含错误信息。
     * 消息体(Body)说明：响应消息体可以包含更多的除
     * 头部“原因说明”以外的隐含错误信息。
     */
    Unauthorized(401),
    /**
     * “Forbidden”(禁止)-该请求是不允许的，因为服
     * 务器拒绝处理该请求。出现这种情况的通常原因是
     * 设备不支持所请求的功能。
     * 头部（Header）说明：通过 HTTP 状态头部发送的原
     * 因说明(Reason-Phrase)可包含错误信息。
     * 消息体(Body)说明：响应消息体可以包含更多的除
     * 头部“原因说明”以外的隐含错误信息。
     */
    Forbidden(403),
    /**
     * “Not Found”(找不到)-所请求的资源不存在。
     * 头部(Header)说明：无。
     * 消息体(Body)说明：无。
     */
    NotFound(404),
    /**
     * “Method Not Allowed”(方法不允许)-请求所采用
     * 的 HTTP 方法对该资源不支持，因为{API 协议}规范
     * 不允许该方法。如果设备不支持该功能，但是有效
     * 的{API 协议}操作，则应返回 403 响应码。
     * 头部(Header)说明：Allow 头部字段列出针对该资源
     * 所支持的 HTTP 方法。
     * 消息体(Body)说明：无。
     */
    MethodNotAllowed(405),
    /**
     * “Conflict”(冲突)-所执行的操作与内部状态或正
     * 在执行的过程冲突。这是一种过渡状态，延后一定
     * 时间可以重试该操作。
     * 头部(Header）说明：无。
     * 消息体(Body)说明：无。
     */
    Conflict(409),
    /**
     * “Internal Server Error”(内部服务器错误)-发
     * 生了内部服务器错误。
     * 头部(Header)说明：无。
     * 消息体(Body)说明：无。
     */
    InternalServerError(500),
    /**
     * “Service Unavailable”(服务不可用)-HTTP 服务
     * 器是正常的，但 REST 服务不可用。这通常是因为客
     * 户请求太多引起的。
     * 头部(Header)说明：Retry-After 头部字段建议客户
     * 什么时间再尝试重新发送请求。
     * 消息体(Body)说明：无。
     */
    ServiceUnavailable(503),

    Unknown(0),
    RequestFail(-999);

    private int code;

    AopErrorCode(int i) {
        this.code = i;
    }

    public static AopErrorCode valueOf(int status) {
        for (AopErrorCode item : values()) {
            if (item.code == status) {
                return item;
            }
        }

        if (status > 500) {
            return ServiceUnavailable;
        }
        return Unknown;
    }

    public int getCode() {
        return code;
    }
}
