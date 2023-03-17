package com.masamiaoi.exampledemo.service.impl;

import com.masamiaoi.exampledemo.service.PdfTemplateService;
import com.masamiaoi.exampledemo.util.PdfUtils;
import org.springframework.stereotype.Service;

/**
 * @author: MASAMIAOI
 * @description: pdf 模板测试类
 * @date: 2023/3/17 13:14
 * @version: 1.0.0
 */
@Service
public class PdfTemplateServiceImpl implements PdfTemplateService {


    /**
     * 下载 pdf
     */
    @Override
    public void downloadPdf() {
        PdfUtils.createPdfByTemplate();
    }
}
