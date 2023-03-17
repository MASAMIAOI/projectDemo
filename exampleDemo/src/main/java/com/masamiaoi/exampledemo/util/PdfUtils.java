package com.masamiaoi.exampledemo.util;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.*;
import org.apache.commons.lang3.StringUtils;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: MASAMIAOI
 * @description: pdf 工具类
 * @date: 2023/3/17 13:20
 * @version: 1.0.0
 */
public class PdfUtils {

    /**
     * 根据模板创建生成pdf
     *
     * @param map          模板中的表单数据 key-表单属性值；value-值
     * @param templatePath 模板路径
     * @return 返回生成的pdf文件路径
     */
    public static void createPdfByTemplate() {
        String templatePath = "F:\\ms\\ms\\javaproject\\pdfTest\\测试.pdf";
        //图片位置
        String imgpath = "F:\\ms\\ms\\javaproject\\pdfTest\\1.jpg";
        // 生成的新文件路径
        String pdfPath = "F:\\ms\\ms\\javaproject\\pdfTest\\测试pdf.pdf";
        PdfStamper stamper = null;
        PdfReader reader = null;
        FileOutputStream fos = null;
        ByteArrayOutputStream bos = null;
        try {
            //设置中文字体
            BaseFont bf = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
            // 读取pdf模板
            reader = new PdfReader(templatePath);
            //获取字节数组输出流
            bos = new ByteArrayOutputStream();
            //操作pdf文件
            stamper = new PdfStamper(reader, bos);
            //获取pdf模板中的属性
            AcroFields form = stamper.getAcroFields();
            //为属性设置字体
            form.addSubstitutionFont(bf);
            //处理模板文字部分
            Map<String, String> map = new HashMap<String, String>();
            map.put("age", "99999");
            map.put("name", "aaaa有限公司");
            map.put("address", "安徽省马鞍山市花山区民族大道1099号大学城美食城A333092湖北省武汉社想下去金融港青年公寓");
            map.put("fzrq", "2020-11-23");
            map.put("yxqz", "2030-11-23");
            for (Map.Entry<String, String> entry : map.entrySet()) {
                form.setFieldProperty(entry.getKey(), "textsize", 8f, null);
                form.setField(entry.getKey(), entry.getValue());
            }
            //处理模板图片
            if (StringUtils.isNotBlank(imgpath) && null != form.getFieldPositions("img")) {
                int pageNo = form.getFieldPositions("img").get(0).page;
                Rectangle signRect = form.getFieldPositions("img").get(0).position;
                float x = signRect.getLeft();
                float y = signRect.getBottom();
                //根据路径读取图片
                Image image = Image.getInstance(imgpath);
                //获取图片页面
                PdfContentByte under = stamper.getOverContent(pageNo);
                //图片大小自适应
                image.scaleToFit(signRect.getWidth(), signRect.getHeight());
                //添加图片
                image.setAbsolutePosition(x, y);
                under.addImage(image);
            }
            // 如果为false，生成的PDF文件可以编辑，如果为true，生成的PDF文件不可以编辑
            stamper.setFormFlattening(true);
            //生成pdf路径
            fos = new FileOutputStream(pdfPath);
            fos.write(bos.toByteArray());
            fos.flush();
            System.out.println("pdf模板生成了");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != stamper) {
                try {
                    stamper.close();
                } catch (IOException | DocumentException e) {
                    throw new RuntimeException(e);
                }
            }
            if (null != reader) {
                reader.close();
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

}
