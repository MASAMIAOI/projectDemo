package com.masamiaoi.file;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.*;
import java.nio.file.Files;
import java.util.Date;

@SpringBootTest
class FileApplicationTests {

    @Test
    void contextLoads() {
    }


    /**
     * 文件内容读取
     */
    @Test
    void readFile() {
        try {
            BufferedReader in = new BufferedReader(new FileReader("F:\\ms\\ms\\javaproject\\projectdemo-master\\nowcoder.txt"));
            String str;
            while ((str = in.readLine()) != null) {
                System.out.println(str);
            }
            System.out.println(str);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 文件写入测试
     */
    @Test
    void writeFile() {
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter("nowcoder.txt"));
            out.write("牛客教程");
            out.close();
            System.out.println("文件创建成功！");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 文件删除测试
     */
    @Test
    void deleteTest() {
        try {
            File file = new File("F:\\ms\\ms\\javaproject\\projectdemo-master\\nowcoder.txt");
            if (file.delete()) {
                System.out.println(file.getName() + " 文件已被删除！");
            } else {
                System.out.println("文件删除失败！");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * copy 文件测试
     * 文件从一个文件赋值到另一个文件
     */
    @Test
    void copyTest() {
        try {
            BufferedWriter out1 = new BufferedWriter(new FileWriter("srcfile"));
            out1.write("string to be copied\n");
            out1.close();
            InputStream in = Files.newInputStream(new File("srcfile").toPath());
            OutputStream out = Files.newOutputStream(new File("destnfile").toPath());
            byte[] buf = new byte[1024];
            int len;
            while ((len = in.read(buf)) > 0) {
                out.write(buf, 0, len);
            }
            in.close();
            out.close();
            BufferedReader in1 = new BufferedReader(new FileReader("destnfile"));
            String str;
            while ((str = in1.readLine()) != null) {
                System.out.println(str);
            }
            in1.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * 文件中追加内容测试
     */
    @Test
    void addContent() {
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter("filename", true));
            out.write("aString1\n");
            out.close();
            out = new BufferedWriter(new FileWriter("filename", true));
            out.write("aString2");
            out.close();
            BufferedReader in = new BufferedReader(new FileReader("filename"));
            String str;
            while ((str = in.readLine()) != null) {
                System.out.println(str);
            }
            in.close();
        } catch (IOException e) {
            System.out.println("exception occoured" + e);
        }
    }

    /**
     * 临时文件
     */
    @Test
    void test() {
        try {
            File temp = File.createTempFile("test", ".txt");
            System.out.println("文件路径: " + temp.getAbsolutePath());
            temp.deleteOnExit();
            BufferedWriter out = new BufferedWriter(new FileWriter(temp));
            out.write("aString");
            System.out.println("临时文件已创建:");
            out.close();
        } catch (IOException ex) {
            System.out.println("exception occoured" + ex);
        }
    }

    /**
     * 临时文件创建
     */
    @Test
    void test2() {
        File f = null;
        try {
            // 创建临时文件
            f = File.createTempFile("tmp", ".txt", new File("C:/"));
            // 输出绝对路径
            System.out.println("File path: " + f.getAbsolutePath());
            // 终止后删除临时文件
            f.deleteOnExit();
            // 创建临时文件
            f = File.createTempFile("tmp", null, new File("D:/"));
            // 输出绝对路径
            System.out.print("File path: " + f.getAbsolutePath());
            // 终止后删除临时文件
            f.deleteOnExit();
        } catch (Exception e) {
            // 如果有错误输出内容
            e.printStackTrace();
        }
    }


    /**
     * 修改文件的时间
     */
    @Test
    void dataTest() {
        try {
            File fileToChange = new File("C:/myjavafile.txt");
            fileToChange.createNewFile();
            Date filetime = new Date(fileToChange.lastModified());
            System.out.println(filetime.toString());
            System.out.println(fileToChange.setLastModified(System.currentTimeMillis()));
            filetime = new Date(fileToChange.lastModified());
            System.out.println(filetime.toString());
        } catch (IOException ex) {
            System.out.println(ex);
        }

    }


    /**
     * 获取文件大小
     */
    @Test
    void fileSize() {
        long size = getFileSize("F:\\ms\\ms\\javaproject\\projectdemo-master\\file\\filename");
        System.out.println("java.txt文件大小为: " + size);
    }

    /**
     * 文件重命名
     */
    @Test
    void rename() {
        File oldName = new File("F:\\ms\\ms\\javaproject\\projectdemo-master\\file\\filename");
        File newName = new File("F:\\ms\\ms\\javaproject\\projectdemo-master\\file\\est");
        if (oldName.renameTo(newName)) {
            System.out.println("已重命名");
        } else {
            System.out.println("Error");
        }
    }

    /**
     * 设置文件只读
     */
    @Test
    void setReadOnly() {
        File file = new File("C:/java.txt");
        System.out.println(file.setReadOnly());
        System.out.println(file.canWrite());
    }

    /**
     * 检测文件是否存在
     */
    @Test
    void exists() {
        File file = new File("C:/java.txt");
        System.out.println(file.exists());
    }

    /**
     * 指定目录中创建文件
     */
    @Test
    void createFile() throws IOException {
        File file = null;
        File dir = new File("D://");
        file = File.createTempFile
                ("JavaTemp", ".javatemp", dir);
        System.out.println(file.getPath());
    }

    /**
     * 创建文件
     */
    @Test
    void createFile2() throws IOException {
        File file = new File("C:/myfile.txt");
        if (file.createNewFile())
            System.out.println("文件创建成功！");
        else
            System.out.println("出错了，该文件已经存在。");
    }

    /**
     * 文件路径比较
     */
    @Test
    void compareTo() {
        File file1 = new File("C:/File/demo1.txt");
        File file2 = new File("C:/java/demo1.txt");
        if (file1.compareTo(file2) == 0) {
            System.out.println("文件路径一致！");
        } else {
            System.out.println("文件路径不一致！");
        }
    }


    /**
     * 获取文件大小
     */
    public static long getFileSize(String filename) {
        File file = new File(filename);
        if (!file.exists() || !file.isFile()) {
            System.out.println("文件不存在");
            return -1;
        }
        return file.length();
    }

    public void change(String s) {
        s = "change";
    }

    @Test
    public void testChange() {
        String s = "hello";
        change(s);
        System.out.println(s);
    }

    @Test
    public void testIplus() {
        int i = 1;
        System.out.println(i++);
        System.out.println(++i);
    }

}
