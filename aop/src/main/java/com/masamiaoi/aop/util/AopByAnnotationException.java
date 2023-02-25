package com.masamiaoi.aop.util;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

/**
 * @author shenruiyan
 * @version 1.0
 * @Description: 自定义注解截断 exception
 * @date 2023/2/24 - 16:30
 */
@Data
@Slf4j
public class AopByAnnotationException extends RuntimeException {

    /**
     * 错误状态码
     */
    private AopErrorCode errorCode;

    public AopByAnnotationException(AopErrorCode errorCode) {
        super("");
        this.errorCode = errorCode;
    }

    public AopByAnnotationException(AopErrorCode errorCode, String body) {
        super(body);
        this.errorCode = errorCode;
    }

    public void response(HttpServletResponse resp) {

        resp.setStatus(getErrorCode().getCode());
        try {
            if (StringUtils.isEmpty(getMessage())) {
                return;
            }

            resp.getWriter().print(getMessage());
        } catch (IOException e) {
            log.error("response", e);
        }
    }

    public static class BadRequestException extends AopByAnnotationException {
        public BadRequestException(String body) {
            super(AopErrorCode.BadRequest, body);
        }
    }

    public static class UnauthorizedException extends AopByAnnotationException {
        private String wwwAuthenticate;

        public UnauthorizedException(String wwwAuthenticate, String body) {
            super(AopErrorCode.Unauthorized, body);

            this.wwwAuthenticate = wwwAuthenticate;
        }

        @Override
        public void response(HttpServletResponse resp) {

            resp.setHeader("WWW-Authenticate", wwwAuthenticate);

            resp.setStatus(getErrorCode().getCode());
        }
    }

    public static class ServiceUnavailableException extends AopByAnnotationException {
        LocalDateTime retryAfter;

        public ServiceUnavailableException(LocalDateTime retryAfter, String body) {
            super(AopErrorCode.ServiceUnavailable, body);

            this.retryAfter = retryAfter;
        }

        public ServiceUnavailableException() {
            this(LocalDateTime.now().plusMinutes(1), "");
        }

        public ServiceUnavailableException(String s) {
            this(LocalDateTime.now().plusMinutes(1), s);
        }

        @Override
        public void response(HttpServletResponse resp) {

            resp.setDateHeader("Retry-After", retryAfter.toEpochSecond(ZoneOffset.UTC));

            super.response(resp);
        }
    }

}
