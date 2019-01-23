package io.renren.modules.CTG.controller;

import io.renren.modules.CTG.dto.CommonDTO;
import io.renren.modules.CTG.entity.PageEntity;
import io.renren.modules.CTG.service.DatabaseService;
import io.renren.modules.CTG.utils.CommonDTOUtil;
import io.renren.modules.CTG.utils.FileUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * @author tianyi
 * @date 2019-01-17 08:29
 */
@RestController
@RequestMapping("/file")
@Slf4j
public class FileController {

    @Autowired
    private DatabaseService databaseService;

    /**
     * @apiDefine File 文件
     */

    /**
     *  @apiDefine CommonDTO
     *  @apiSuccess {Integer} resultCode 响应结果
     *  @apiSuccess {String} resultMsg 结果描述
     *  @apiSuccess {Object} data 数据主体
     */

    /**
     * @api {get} /file/down/sample 4.下载上传示例及说明zip
     * @apiGroup File
     * @apiUse CommonDTO
     */
    @GetMapping("/down/sample")
    public CommonDTO downSample(HttpServletResponse response, PageEntity pageEntity){
        try {
            FileInputStream fileInputStream = new FileInputStream(new File(".\\示例文件.zip"));
            byte[] byt = new byte[fileInputStream.available()];
            fileInputStream.read(byt);

            response.setContentType("application/x-zip-compressed");
            OutputStream out = response.getOutputStream();
            out.write(byt);
            //关闭响应输出流
            out.close();

            return CommonDTOUtil.success();
        }catch (Exception e){
            e.printStackTrace();
            return CommonDTOUtil.error(500,e.getMessage());
        }
    }

    /**
     * @api {get} /file/down/database 下载数据库的所有数据csv
     * @apiGroup File
     * @apiUse CommonDTO
     */
    @GetMapping("/down/database")
    public CommonDTO downDatabase(HttpServletResponse response, PageEntity pageEntity){
        try {
            return CommonDTOUtil.success();
        }catch (Exception e){
            e.printStackTrace();
            return CommonDTOUtil.error(500,e.getMessage());
        }
    }

    /**
     * @api {get} /file/down/line 下载指定包号的折线数据zip
     * @apiGroup File
     * @apiParam {String} name 数据包的包号
     * @apiUse CommonDTO
     */
    @GetMapping("/down/line")
    public CommonDTO downLinePackage(HttpServletResponse response, PageEntity pageEntity){
        try {
            return CommonDTOUtil.success();
        }catch (Exception e){
            e.printStackTrace();
            return CommonDTOUtil.error(500,e.getMessage());
        }
    }

    // 按名字获取缓存文件
//    @GetMapping("/down/cache")
//    public CommonDTO downSource(HttpServletResponse response, PageEntity pageEntity){
//        try {
//            return CommonDTOUtil.success();
//        }catch (Exception e){
//            e.printStackTrace();
//            return CommonDTOUtil.error(500,e.getMessage());
//        }
//    }

//    // 获取所有折线图数据文件zip压缩包
//    @GetMapping("/down/allLine")
//    public CommonDTO downAllLine(HttpServletResponse response){
//        try {
//            // FileUtil.zipFiles(FileUtil.linePath, ); TODO 如何压缩路径内的多个文件夹?
//            return CommonDTOUtil.success();
//        }catch (Exception e){
//            e.printStackTrace();
//            return CommonDTOUtil.error(500,e.getMessage());
//        }
//    }

    /**
     * @api {post} /file/up/zip 3.上传数据文件（注意格式）
     * @apiGroup File
     * @apiParam {File} upFile 上传的文件
     * @apiUse CommonDTO
     */
    @PostMapping("/up/zip")
    public CommonDTO upExcel(@RequestParam("upFile") MultipartFile upFile,  HttpServletResponse response, PageEntity pageEntity){
        if (upFile.isEmpty()) {
            return CommonDTOUtil.error(444,"文件为空");
        }
        try {

            //2、通过IO流将MultipartFile转为java.io.File
            InputStream input = upFile.getInputStream();
            byte[] byt = new byte[input.available()];
            input.read(byt);

            //3、获取文件名称：testKey
            String originalFilename = upFile.getOriginalFilename();
            String kind = originalFilename.substring(originalFilename.lastIndexOf("."));
            String testKey = originalFilename;
            log.info("上传的文件名为：" + originalFilename);
            log.info("上传的后缀名为：" + kind);

            //4、将byte数组转换为IOFile：
            File path = new File(FileUtil.cachePath);
            if (!path.exists()) {
                path.mkdir();
            }
            File iofile = new File(path +"/"+ testKey);
            if (!iofile.exists()) {
                iofile.createNewFile();
            }
            OutputStream output = null;
            output = new FileOutputStream(iofile);
            BufferedOutputStream bufferedOutput = new BufferedOutputStream(output);
            bufferedOutput.write(byt);
            FileUtil.printFilePropertis(iofile.getPath());
            //5、关闭用完的流
            bufferedOutput.close();
            output.close();
            input.close();

            // 选用需要的内容类型
//            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
//            response.setContentType("application/x-zip-compressed");
//            OutputStream out = response.getOutputStream();
//            out.write(byt);
//            //关闭响应输出流
//            out.close();

            long upTime = System.currentTimeMillis();
            String cacheDirPath = FileUtil.cachePath + "\\" + upTime;
            FileUtil.unZip(iofile.getCanonicalPath(), cacheDirPath);
            File cacheDir = new File(cacheDirPath);

            String[] zipFileNames = cacheDir.list(new FilenameFilter() {
                @Override
                public boolean accept(File dir, String name) {
                    return name.endsWith(".zip");
                }
            });
            for (String zipFileName:
                    zipFileNames) {
                FileUtil.unZip(cacheDirPath + "\\" + zipFileName, FileUtil.linePath + "\\" + zipFileName.substring(0, zipFileName.lastIndexOf('.')));
            }

            StringBuffer rtMsg = new StringBuffer();
            String[] xlsFileNames = cacheDir.list(new FilenameFilter() {
                @Override
                public boolean accept(File dir, String name) {
                    return name.endsWith(".xls");
                }
            });
            for (String xlsFieName:
                    xlsFileNames) {
                rtMsg.append(databaseService.importDataFromExcelFile(cacheDirPath + "\\" +xlsFieName, true, upTime)).append("\n");
            }

            return CommonDTOUtil.success(rtMsg.toString());
        } catch  (Exception e){
            e.printStackTrace();
            return CommonDTOUtil.error(500,e.getMessage());
        }
    }

}
