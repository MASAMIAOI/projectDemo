package com.masamiaoi.exampledemo.rest;

import com.masamiaoi.exampledemo.service.PdfTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: MASAMIAOI
 * @description: PDF 测试
 * @date: 2023/3/17 13:10
 * @version: 1.0.0
 */
@RestController
@RequestMapping(path = "/pdf")
public class PdfTemplateRest {

    /**
     * pdf service
     */
    @Autowired
    PdfTemplateService pdfTemplateService;

    /**
     * 下载 pdf
     */
    @RequestMapping(method = RequestMethod.POST, path = "/downloadPdf")
    public void downloadPdf() {
        pdfTemplateService.downloadPdf();
    }
}
