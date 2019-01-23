package io.renren.modules.CTG.controller.test;

import io.renren.modules.CTG.dto.CommonDTO;
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
 * @date 2018-12-10 22:48
 */
@RestController
@RequestMapping("/testfile")
@Slf4j
public class TestFileController {

    @Autowired
    private DatabaseService databaseService;

    @GetMapping("/try")
    public String sayHello(){
        File path = new File("./lines");
        File myFilePath = new File(path + "/2018-12-10_1.file");
        try {
            if (!path.exists()) {
                path.mkdir();
            }
            if (!myFilePath.exists()) {
                myFilePath.createNewFile();
            }
            FileWriter resultFile = new FileWriter(myFilePath);
            PrintWriter myFile = new PrintWriter(resultFile);
            myFile.println("211 566 899");
            resultFile.close();
            FileUtil.printFilePropertis(myFilePath.getPath());
        }
        catch (Exception e) {
            System.out.println("新建目录操作出错");
            e.printStackTrace();
        }
        return "ok";
    }

    @GetMapping("/try2")
    public String fileTry2(){
        String filePath = "F:\\study\\jidi\\2018\\下\\206数据管理系统\\renren-fast\\FILES\\sqlExcel\\1544968754658.xls";
        try {
            System.out.println("try2..");
            return databaseService.importDataFromExcelFile(filePath,true, 1098L);
        }
        catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }

    @PostMapping("/up")
    public String uploadF(@RequestParam("testF") MultipartFile file,HttpServletResponse response) {
        if (file.isEmpty()) {
            return "文件为空";
        }
        try {
            // 获取文件名
            String fileName = file.getOriginalFilename();
            log.info("上传的文件名为：" + fileName);
            // 获取文件的后缀名
            String suffixName = fileName.substring(fileName.lastIndexOf("."));
            log.info("上传的后缀名为：" + suffixName);

            //2、通过IO流将MultipartFile转为java.io.File
            InputStream input = file.getInputStream();
            byte[] byt = new byte[input.available()];
            input.read(byt);

            //3、获取文件名称：testKey
            String originalFilename = file.getOriginalFilename();
            String kind = originalFilename.substring(originalFilename.lastIndexOf("."));
            String testKey = System.currentTimeMillis() + kind;

            //4、将byte数组转换为IOFile：
            File path = new File("./FILES/sqlExcel");
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
            //5、关闭用完的流  【不然后面无法删除本地缓存】
            bufferedOutput.close();
            output.close();
            input.close();

//            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            response.setContentType("application/x-zip-compressed");
            OutputStream out = response.getOutputStream();
            out.write(byt);
            //关闭响应输出流
            out.close();


            return "ok";
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return e.getMessage();
        } catch (IOException e) {
            e.printStackTrace();
            return e.getMessage();
        }catch (Exception e){
            e.printStackTrace();
            return e.getMessage();
        }
    }

    @GetMapping("/down")
    public CommonDTO downF(HttpServletResponse response){
        try {
            File path = new File("./FILES/sqlExcel");
            if (!path.exists()) {
                path.mkdir();
            }
            File iofile = new File(path + "/sample.zip");
            if (!iofile.exists()) {
                iofile.createNewFile();
            }
            FileInputStream inputStream = new FileInputStream(iofile);
            byte[] byt = new byte[inputStream.available()];
            log.warn("inputStream.available()={}, byt.length={}",inputStream.available(),byt.length);
            inputStream.read(byt);
            response.setContentType("application/x-zip-compressed");
            OutputStream out = response.getOutputStream();
            out.write(byt);
            inputStream.close();
            out.close();
            return CommonDTOUtil.success(byt);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return CommonDTOUtil.error(404, e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
            return CommonDTOUtil.error(500, e.getMessage());
        }
    }

}
