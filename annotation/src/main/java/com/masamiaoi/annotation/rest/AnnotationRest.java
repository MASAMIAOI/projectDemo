package com.masamiaoi.annotation.rest;

import com.masamiaoi.annotation.authIdentity.AuthIdentity;
import com.masamiaoi.annotation.authIdentity.FileParam;
import com.masamiaoi.annotation.authIdentity.FileValid;
import com.masamiaoi.annotation.authIdentity.Limit;
import com.masamiaoi.annotation.type.RestResultVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.TimeUnit;

/**
 * @author shenruiyan
 * @version 1.0
 * @Description: 枚举 rest
 * @date 2023/2/22 - 15:46
 */
@Slf4j
@RestController
@RequestMapping(path = "/annotation")
public class AnnotationRest {


    /**
     * 测试环绕测试枚举
     */
    @AuthIdentity(1)
    @RequestMapping(method = {RequestMethod.POST, RequestMethod.GET}, path = "/testOne")
    public RestResultVo annotationRest() {
        return RestResultVo.ok();
    }


    /**
     * 文件上传测试枚举
     */
    @FileValid
    @RequestMapping(method = RequestMethod.POST, path = "/upload")
    public RestResultVo upload(@FileParam(suffix = {"doc", "txt"}) MultipartFile file, HttpServletRequest request, HttpServletResponse response) {
        return RestResultVo.ok();
    }

    /**
     * 限流测试枚举
     */

    @RequestMapping(method = {RequestMethod.POST, RequestMethod.GET}, path = "/limit2")
    @Limit(key = "limit2", permitsPerSecond = 1, timeout = 1, timeunit = TimeUnit.SECONDS, msg = "limit2 -- 当前排队人数较多，请稍后再试！")
    public RestResultVo limit2() {
        return RestResultVo.ok();
    }


    @RequestMapping(method = {RequestMethod.POST, RequestMethod.GET}, path = "/limit3")
    @Limit(key = "limit3", permitsPerSecond = 2, timeout = 1, timeunit = TimeUnit.SECONDS, msg = "limit3 -- 系统繁忙，请稍后再试！")
    public RestResultVo limit3() {
        return RestResultVo.ok();
    }

}
