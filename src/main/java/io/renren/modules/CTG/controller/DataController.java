package io.renren.modules.CTG.controller;

import io.renren.modules.CTG.dto.CommonDTO;
import io.renren.modules.CTG.entity.PageEntity;
import io.renren.modules.CTG.service.DatabaseService;
import io.renren.modules.CTG.utils.CommonDTOUtil;
import io.renren.modules.CTG.utils.FileUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.text.ParseException;

/**
 * @author tianyi
 * @date 2019-01-17 08:50
 */
@RestController
@RequestMapping("/data")
@Slf4j
public class DataController {

    @Autowired
    private DatabaseService databaseService;

    /**
     * @apiDefine Data 数据
     */

    /**
     *  @apiDefine CommonDTO
     *  @apiSuccess {Integer} resultCode 响应结果
     *  @apiSuccess {String} resultMsg 结果描述
     *  @apiSuccess {Object} data 数据主体
     */

    // TODO 按包获取折线文件数据内容
    @GetMapping("/linePackage/download")
    public CommonDTO getPackageLine(HttpServletResponse response, PageEntity pageEntity){
        try {
            return CommonDTOUtil.success(databaseService.lineDataByPackage(pageEntity));
        } catch (ParseException e) {
            e.printStackTrace();
            return CommonDTOUtil.error(500,"日期转换时出错:\n"+e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
            return CommonDTOUtil.error(444,"文件流异常:\n"+e.getMessage());
        } catch (Exception e){
            e.printStackTrace();
            return CommonDTOUtil.error(500,"未知错误:\n"+e.getMessage());
        }
    }

    /**
     * @api {get} /data/ctgData/byPage 1.按页获取ctg数据
     * @apiGroup Data
     * @apiParam {int} page 页码
     * @apiSuccessExample Success-Request:
     * {
     *     page:1
     * }
     * @apiUse CommonDTO
     */
    @GetMapping("/ctgData/byPage")
    public CommonDTO pageCtg(PageEntity pageEntity){
        return CommonDTOUtil.success(databaseService.ctgDataPageable(pageEntity));
    }


    /**
     * @api {get} /data/linePackage/list 2.获取折线包名列表（用于按包下载）
     * @apiGroup Data
     * @apiUse CommonDTO
     */
    @GetMapping("/linePackage/list")
    public CommonDTO getLinePackageList(HttpServletResponse response, PageEntity pageEntity){
        File cacheDir = new File(FileUtil.linePath);
        return CommonDTOUtil.success(cacheDir.list());
    }

//
//    @GetMapping("/judgeResult/byPage")
//    public CommonDTO pageJudge(PageEntity pageEntity){
//        return CommonDTOUtil.success(databaseService.judgeDataPageable(pageEntity));
//    }

//
//    @GetMapping("/cache/list")
//    public CommonDTO getCacheList(HttpServletResponse response, PageEntity pageEntity){
//        File cacheDir = new File(FileUtil.cachePath);
//        return CommonDTOUtil.success(cacheDir.list());
//    }

}
