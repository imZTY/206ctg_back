package io.renren.modules.CTG.utils;

import java.io.*;
import java.util.Date;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/**
 * @author tianyi
 * @date 2018-12-14 08:37
 */
public class FileUtil {

    public static String rootPath = "./fileManager/";
    public static String cachePath = rootPath + "cache";
    public static String linePath = rootPath + "line";
    public static String samplePath = rootPath + "sample";

    public static String getLineFilePath(int packageId, long upTime, int doctorId, int doctorGrade){
        return "";
    }

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

    public static void main(String[] args){
//        File dir = new File(FileUtil.linePath);
//        for (String per:
//             dir.list()) {
//            System.out.println(per);
//        }


//        String dirPath = "F:\\study\\jidi\\2018\\下\\206数据管理系统\\0115取得新数据\\zip静态操作测试";
//
//        // 【可用于返回所有文件名 接口的设计】
//        File dir = new File(dirPath);
//        // 结合文件名过滤器遍历出满足条件的子文件/子文件夹
//        String[] names = dir.list(new FilenameFilter() {
//            @Override
//            public boolean accept(File dir, String name) {
//                return name.endsWith(".txt");
//            }
//        });
//        System.out.println(names[0]);

        try {
            //zipFiles(dirPath, names, "first.zip");
            unZip("F:\\study\\jidi\\2018\\下\\206数据管理系统\\0115取得新数据\\zip静态操作测试\\testSource.zip", "F:\\study\\jidi\\2018\\下\\206数据管理系统\\0119中途更新发布\\中途zip测试");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 压缩处理
     * From 勇闯天涯zfc https://www.cnblogs.com/zfc-java/p/8270612.html
     * @param dirName 无 “\\” 的目录名
     * @param srcFilesNames 待压缩文件名称数组
     * @param zipFileName 压缩后的文件名
     * @throws IOException
     */
    public static void zipFiles(String dirName, String[] srcFilesNames, String zipFileName) throws IOException {
        File zipFile = new File(dirName + "\\" + zipFileName);
        // 判断压缩后的文件存在不，不存在则创建
        if (!zipFile.exists()) {
            try {
                zipFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        // 创建 FileOutputStream 对象
        FileOutputStream fileOutputStream = null;
        // 创建 ZipOutputStream
        ZipOutputStream zipOutputStream = null;
        // 创建 FileInputStream 对象
        FileInputStream fileInputStream = null;

        try {
            // 实例化 FileOutputStream 对象
            fileOutputStream = new FileOutputStream(zipFile);
            // 实例化 ZipOutputStream 对象
            zipOutputStream = new ZipOutputStream(fileOutputStream);
            // 创建 ZipEntry 对象
            ZipEntry zipEntry = null;
            // 遍历源文件数组
            for (int i = 0; i < srcFilesNames.length; i++) {
                // 将源文件数组中的当前文件读入 FileInputStream 流中
                fileInputStream = new FileInputStream(new File(dirName + "\\" + srcFilesNames[i]));
                // 实例化 ZipEntry 对象，源文件数组中的当前文件
                zipEntry = new ZipEntry(new File(dirName + "\\" + srcFilesNames[i]).getName());
                zipOutputStream.putNextEntry(zipEntry);
                // 该变量记录每次真正读的字节个数
                int len;
                // 定义每次读取的字节数组
                byte[] buffer = new byte[1024];
                while ((len = fileInputStream.read(buffer)) > 0) {
                    zipOutputStream.write(buffer, 0, len);
                }
            }
            zipOutputStream.closeEntry();
            zipOutputStream.close();
            fileInputStream.close();
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
            zipOutputStream.closeEntry();
            zipOutputStream.close();
            fileInputStream.close();
            fileOutputStream.close();
            throw e;
        }

    }


    /**
     * 解压函数。抄来的代码，未调整
     * @param zipFilePath 压缩包路径
     * @param finalDirPath 内容解压至此文件夹
     * @throws IOException
     */
    public static void unZip(String zipFilePath, String finalDirPath) throws IOException {
        File file = new File(zipFilePath) ;	// 定义压缩文件名称
        File dirFile = new File(finalDirPath) ;	// 输出文件的时候要有文件夹的操作
        if (!dirFile.exists()){
            dirFile.mkdirs();
        }
        ZipFile zipFile = new ZipFile(file) ;	// 实例化ZipFile对象

        ZipInputStream zipInput = null ;	// 定义压缩输入流
        OutputStream out = null ;	// 定义输出流，用于输出每一个实体内容
        InputStream input = null ;	// 定义输入流，读取每一个ZipEntry
        ZipEntry entry = null ;	// 每一个压缩实体

        zipInput = new ZipInputStream(new FileInputStream(file)) ;	// 实例化ZIpInputStream
        while((entry = zipInput.getNextEntry())!=null){	// 得到一个压缩实体
            File outFile = new File(finalDirPath + "\\" + entry.getName()) ;	// 定义输出的文件路径
            System.out.println(outFile.getCanonicalPath());
            if(!outFile.exists()){	// 判断输出文件是否存在
                outFile.createNewFile() ;	// 创建文件
            }
            input = zipFile.getInputStream(entry) ;	// 得到每一个实体的输入流
            out = new FileOutputStream(outFile) ;	// 实例化文件输出流
            int temp = 0 ;
            while((temp=input.read())!=-1){
                out.write(temp) ;
            }
            input.close() ;		// 关闭输入流
            out.close() ;	// 关闭输出流
        }
        input.close();
    }
}
