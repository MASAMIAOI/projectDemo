package com.masamiaoi.exampledemo.service.impl;

import com.masamiaoi.exampledemo.service.PdfTemplateService;
import com.masamiaoi.exampledemo.util.PdfUtils;
import org.springframework.stereotype.Service;

import java.util.HashMap;

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
        HashMap<String, String> inputMap = new HashMap<>();
        inputMap.put("AssetName", "99999");
        inputMap.put("AssetId", "aaaa有限公司");
        inputMap.put("AssetTypeName", "安徽省马鞍山市花山区民族大道1099号大学城美食城A333092湖北省武汉社想下去金融港青年公寓");
        PdfUtils.pdfExport(inputMap);
    }
}
