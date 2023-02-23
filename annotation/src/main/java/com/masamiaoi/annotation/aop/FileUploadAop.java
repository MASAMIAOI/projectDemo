package com.masamiaoi.annotation.aop;

import com.masamiaoi.annotation.authIdentity.FileParam;
import com.masamiaoi.annotation.type.RestResultCommonCode;
import com.masamiaoi.annotation.type.RestResultVo;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Arrays;
import java.util.Objects;

/**
 * @author shenruiyan
 * @version 1.0
 * @Description: 文件切割 aop
 * @date 2023/2/23 - 10:13
 */
@Slf4j
@Aspect
@Component
public class FileUploadAop {
    @Pointcut("@annotation(com.masamiaoi.annotation.authIdentity.FileValid)")
    public void pointcut() {

    }

    @Around("pointcut()")
    public Object around(ProceedingJoinPoint pjp) {
        // 参数值
        Object[] args = pjp.getArgs();
        // 方法
        Method method = ((MethodSignature) pjp.getSignature()).getMethod();
        // 参数列表
        Parameter[] mParameters = method.getParameters();
        for (int i = 0; i < mParameters.length; i++) {
            // 判断参数上是否修饰了注解
            if (mParameters[i].isAnnotationPresent(FileParam.class)) {
                // 获取注解进而得到注解上的参数值
                FileParam annotation = mParameters[i].getAnnotation(FileParam.class);
                String[] suffixs = annotation.suffix();
                int size = annotation.size();
                log.info("suffixs: {}, size: {}", suffixs, size);
                // 实际文件大小
                long rSize = 0L;
                // 实际文件后缀
                String suffix = null;
                if (args[i] instanceof MultipartFile) {
                    MultipartFile temp = ((MultipartFile) args[i]);
                    rSize = temp.getSize();
                    suffix = Objects.requireNonNull(temp.getOriginalFilename()).split("\\.")[1];
                    log.info("suffix: {}, rSize: {}", suffix, rSize);
                }
                if (rSize > size) {
                    return RestResultVo.error(RestResultCommonCode.ERROR, String.format("文件大小：%sByte，超过限定大小：%sByte", rSize, size));
                }
                if (!Arrays.asList(suffixs).contains(suffix)) {
                    return RestResultVo.error(RestResultCommonCode.ERROR, String.format("不支持文件上传类型：%s", suffix));
                }
            }
        }

        try {
            return pjp.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return "error";
    }

    @Before("pointcut()")
    public void before() {
        //log.info("before fileUploadAop ...");
    }

    @AfterReturning("pointcut()")
    public void afterReturning() {
        //log.info("afterReturning fileUploadAop ...");
    }

    @After("pointcut()")
    public void after() {
        //log.info("after fileUploadAop ...");
    }

    @AfterThrowing("pointcut()")
    public void afterThrowing() {
        //log.info("afterThrowing fileUploadAop ...");
    }
}
