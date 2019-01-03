package io.renren.modules.CTG.utils;

import java.io.File;
import java.io.IOException;
import java.util.Date;

/**
 * @author tianyi
 * @date 2018-12-14 08:37
 */
public class FileUtil {

    public static void printFilePropertis(String filePath) throws IOException {
        File f = new File(filePath);
        if (f.exists()) {
            System.out.println(f.getName() + "的属性如下： 文件长度为：" + f.length());
            System.out.println(f.isFile() ? "是文件" : "不是文件");
            System.out.println(f.isDirectory() ? "是目录" : "不是目录");
            System.out.println(f.canRead() ? "可读取" : "不");
            System.out.println(f.canWrite() ? "是隐藏文件" : "");
            System.out.println("文件夹的最后修改日期为：" + new Date(f.lastModified()));
            System.out.println("文件的完整路径是：" + f.getCanonicalPath());
        } else {
            System.out.println(f.getName() + "的属性如下：");
            System.out.println(f.isFile() ? "是文件" : "不是文件");
            System.out.println(f.isDirectory() ? "是目录" : "不是目录");
            System.out.println(f.canRead() ? "可读取" : "不");
            System.out.println(f.canWrite() ? "是隐藏文件" : "");
            System.out.println("文件的最后修改日期为：" + new Date(f.lastModified()));
        }
    }
}
