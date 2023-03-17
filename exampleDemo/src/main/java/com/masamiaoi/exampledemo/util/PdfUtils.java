package com.masamiaoi.exampledemo.util;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.AcroFields;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.io.ClassPathResource;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: MASAMIAOI
 * @description: pdf 工具类
 * @date: 2023/3/17 13:20
 * @version: 1.0.0
 */
@Slf4j
public class PdfUtils {

    /**
     * 根据模板创建生成pdf
     *
     * @return
     */
    public static void createPdfByTemplate() {
        // 生成的新文件路径
        String pdfPath = "F:\\ms\\ms\\javaproject\\pdfTest\\测试pdf.pdf";
        // 图片位置
        String imgPath = new ClassPathResource("img/1.jpg").getPath();
        // dpf模板位置
        String templatePath = new ClassPathResource("pdf/workOrder.pdf").getPath();
        PdfStamper stamper = null;
        PdfReader reader = null;
        FileOutputStream fos = null;
        ByteArrayOutputStream bos = null;
        try {
            // 读取pdf模板
            reader = new PdfReader(templatePath);
            //获取字节数组输出流
            bos = new ByteArrayOutputStream();
            //操作pdf文件
            stamper = new PdfStamper(reader, bos);
            //获取pdf模板中的属性
            AcroFields form = stamper.getAcroFields();
            //设置中文字体
//            BaseFont bf = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
//            form.addSubstitutionFont(bf);
            //处理模板文字部分
            Map<String, String> map = new HashMap<String, String>();
            map.put("AssetName", "99999");
            map.put("AssetId", "aaaa有限公司");
            map.put("AssetTypeName", "安徽省马鞍山市花山区民族大道1099号大学城美食城A333092湖北省武汉社想下去金融港青年公寓");
            for (Map.Entry<String, String> entry : map.entrySet()) {
                form.setFieldProperty(entry.getKey(), "textsize", 8f, null);
                form.setField(entry.getKey(), entry.getValue());
            }
            //处理模板图片
            if (StringUtils.isNotBlank(imgPath) && null != form.getFieldPositions("img")) {
                int pageNo = form.getFieldPositions("img").get(0).page;
                Rectangle signRect = form.getFieldPositions("img").get(0).position;
                float x = signRect.getLeft();
                float y = signRect.getBottom();
                //根据路径读取图片
                Image image = Image.getInstance(imgPath);
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

    /**
     * pdf 文档数据填充
     *
     * @param inputMap 需要填入数据
     */
    public static String pdfExport(Map<String, String> inputMap) {
        String filePath = "F:\\ms\\ms\\javaproject\\pdfTest\\";
        // 模板文件路径
        String fileName = DateUtils.locateDateTimeToString(LocalDateTime.now(), DateUtils.Y_M_D__h_m_s) + ".pdf";
        String inputFilePath = new ClassPathResource("pdf/workOrder.pdf").getPath();
        String outputFilePath = filePath + fileName;
        OutputStream os = null;
        PdfStamper ps = null;
        PdfReader reader = null;
        try {
            os = Files.newOutputStream(new File(outputFilePath).toPath());
            reader = new PdfReader(inputFilePath);
            ps = new PdfStamper(reader, os);
            AcroFields form = ps.getAcroFields();
//            BaseFont bf = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
//            form.addSubstitutionFont(bf);
            for (String key : inputMap.keySet()) {
                form.setField(key, inputMap.get(key));
            }
            log.info("===============PDF导出成功=============");
        } catch (Exception e) {
            log.info("===============PDF导出失败=============");
            log.error("pdfExport -> ex", e);
        } finally {
            try {
                if (null != ps) {
                    ps.close();
                }
            } catch (Exception e) {
                log.error("pdfExport -> ex", e);
            }
            try {
                if (null != reader) {
                    reader.close();
                }
            } catch (Exception e) {
                log.error("pdfExport -> ex", e);
            }
            try {
                if (null != os) {
                    os.close();
                }
            } catch (Exception e) {
                log.error("pdfExport -> ex", e);
            }
        }
        return outputFilePath;
    }


    /**
     * 下载 pdf 到前台
     *
     * @param filePath 服务器上的路径地址
     * @param response res
     */
    public void downloadPdf(String filePath, HttpServletResponse response) {
        //输入处理流
        InputStream inputStream = null;
        //输出处理流
        OutputStream outputStream = null;
        File file = new File(filePath);
        //文件名，不含后缀
        String fileSimpleName = filePath.substring(filePath.lastIndexOf("\\") + 1);
        if (!file.exists()) {
            log.error("下载的文件不存在");
            return;
        }
        try {
            //定义处理流
            inputStream = new BufferedInputStream(new FileInputStream(file));
            outputStream = new BufferedOutputStream(response.getOutputStream());
            response.reset();
            response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileSimpleName, "UTF-8"));
            response.addHeader("Content-Length", "" + file.length());
            response.setContentType("application/octet-stream");
            byte[] bytes = new byte[inputStream.available()];
            //将pdf的内容写入bytes中
            inputStream.read(bytes);
            //将bytes中的内容写入
            outputStream.write(bytes);
            //刷新输出流，否则不会写出数据
            outputStream.flush();
        } catch (FileNotFoundException e) {
            log.error("转换的pad文件未找到" + e.getMessage());
        } catch (IOException e) {
            log.error("文件读写异常" + e.getMessage());
        } finally {//关闭输入流、输出流
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                log.error("关闭处理流出现异常" + e.getMessage());
            }
            try {
                if (outputStream != null) {
                    outputStream.close();
                }
            } catch (IOException e) {
                log.error("关闭处理流出现异常" + e.getMessage());
            }
            boolean delete = file.delete();
            log.error("文件删除结果： {}", delete);
        }
    }

}
