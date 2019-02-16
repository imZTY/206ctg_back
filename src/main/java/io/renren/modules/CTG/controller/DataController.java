package io.renren.modules.CTG.controller;

import io.renren.modules.CTG.dto.CommonDTO;
import io.renren.modules.CTG.entity.PageEntity;
import io.renren.modules.CTG.service.DatabaseService;
import io.renren.modules.CTG.utils.CommonDTOUtil;
import io.renren.modules.CTG.utils.FileUtil;
import io.renren.modules.app.annotation.Login;
import io.renren.modules.app.annotation.LoginUser;
import io.renren.modules.app.entity.UserEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.text.ParseException;

import static io.renren.common.utils.ShiroUtils.getUserId;
import static io.renren.modules.app.interceptor.AuthorizationInterceptor.USER_KEY;

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

//
//    @GetMapping("/linePackage/download")
//    public CommonDTO getPackageLine(HttpServletResponse response, PageEntity pageEntity){
//        try {
//            return CommonDTOUtil.success(databaseService.lineDataByPackage(pageEntity));
//        } catch (ParseException e) {
//            e.printStackTrace();
//            return CommonDTOUtil.error(500,"日期转换时出错:\n"+e.getMessage());
//        } catch (IOException e) {
//            e.printStackTrace();
//            return CommonDTOUtil.error(444,"文件流异常:\n"+e.getMessage());
//        } catch (Exception e){
//            e.printStackTrace();
//            return CommonDTOUtil.error(500,"未知错误:\n"+e.getMessage());
//        }
//    }

    /**
     * @api {get} /data/ctgData/byPage 1.按页获取ctg数据
     * @apiGroup Data
     * @apiParam {int} page 页码
     * @apiSuccessExample Success-Request:
     * {
     *     page:1
     * }
     * @apiUse CommonDTO
     * @apiSuccessExample Success-Response:
     * {
     *     内容过长，自行探索
     * }
     */
    @GetMapping("/ctgData/byPage")
    public CommonDTO getCtgDataByPage(PageEntity pageEntity){
        return CommonDTOUtil.success(databaseService.ctgDataPageable(pageEntity));
    }

    /**
     * @api {get} /data/ctgData/byExpertId 【6】按页按医师id获取ctg数据
     * @apiGroup Data
     * @apiParam {int} page 页码
     * @apiParam {int} expertId 医师id
     * @apiSuccessExample Success-Request:
     * {
     *     page:1,
     *     expertId:8
     * }
     * @apiUse CommonDTO
     * @apiSuccessExample Success-Response:
     * {
     *     内容过长，自行探索
     * }
     */
    @GetMapping("/ctgData/byExpertId")
    public CommonDTO getCtgDataByExpertId(PageEntity pageEntity){
        return CommonDTOUtil.success(databaseService.ctgDataByExpertPageable(pageEntity));
    }

    /**
     * @api {get} /data/ctgData/byDifferent 【5】按页按NST不同获取ctg数据
     * @apiGroup Data
     * @apiParam {int} page 页码
     * @apiSuccessExample Success-Request:
     * {
     *     page:1
     * }
     * @apiUse CommonDTO
     * @apiSuccessExample Success-Response:
     * {
     *     内容过长，自行探索
     * }
     */
    @GetMapping("/ctgData/byDifferent")
    public CommonDTO getCtgDataByDifferent(PageEntity pageEntity){
        return CommonDTOUtil.success(databaseService.ctgDataDifferentPageable(pageEntity));
    }

    /**
     * @api {get} /data/linePackage/list 2.获取折线包名列表（用于按包下载）
     * @apiGroup Data
     * @apiUse CommonDTO
     */
    @GetMapping("/linePackage/list")
    public CommonDTO getLinePackageList(PageEntity pageEntity){
        System.out.println("User ID is: "+getUserId());
        File cacheDir = new File(FileUtil.linePath);
        return CommonDTOUtil.success(cacheDir.list());
    }


    @GetMapping("/userId")
    public Long getUserIdByToken(){
        return getUserId();
    }

//
//    @GetMapping("/judgeResult/byPage")
//    public CommonDTO pageJudge(PageEntity pageEntity){
//        return CommonDTOUtil.success(databaseService.judgeDataPageable(pageEntity));
//    }


    /**
     * @api {get} /data/expert/list 【8】获取所有医师列表
     * @apiGroup Data
     * @apiUse CommonDTO
     * @apiSuccessExample Success-Response:
     * {
     *     "resultCode": 200,
    "resultMsg": "成功",
    "data": [
    {
    "id": 16455,
    "expertId": 5,
    "grade": 2,
    "name": "暂无",
    "institution": null,
    "position": null,
    "email": null,
    "phonenumber": null
    },
    {
    "id": 17454,
    "expertId": 7,
    "grade": 2,
    "name": "暂无",
    "institution": null,
    "position": null,
    "email": null,
    "phonenumber": null
    },
    {
    "id": 19451,
    "expertId": 8,
    "grade": 2,
    "name": "暂无",
    "institution": null,
    "position": null,
    "email": null,
    "phonenumber": null
    },
    {
    "id": 19608,
    "expertId": 6,
    "grade": 2,
    "name": "暂无",
    "institution": null,
    "position": null,
    "email": null,
    "phonenumber": null
    }
    ]
     * }
     */
    @GetMapping("/expert/list")
    public CommonDTO getCacheList(){
        return CommonDTOUtil.success(databaseService.getExpertList());
    }


}
