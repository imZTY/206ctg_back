package io.renren.modules.CTG.service;

import io.renren.modules.CTG.entity.CTGData;
import io.renren.modules.CTG.entity.Expert;
import io.renren.modules.CTG.entity.JudgeResult;
import io.renren.modules.CTG.repository.CTGDataRepository;
import io.renren.modules.CTG.repository.ExpertRepository;
import io.renren.modules.CTG.repository.JudgeResultRepository;
import io.renren.modules.CTG.utils.JudgeUtil;
import io.renren.modules.CTG.utils.TimestampUtil;
import jxl.Sheet;
import jxl.Workbook;
import jxl.write.WritableWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;

/**
 * @author tianyi
 * @date 2018-12-16 20:38
 */
@Service
public class DatabaseService {

    @Autowired
    private CTGDataRepository ctgDataRepository;

    @Autowired
    private JudgeResultRepository judgeResultRepository;

    @Autowired
    private ExpertRepository expertRepository;

    /**
     * 将文件导入数据库
     * @param path 文件的完整路径
     * @return
     * @throws Exception
     */
    public String importDataFromExcelFile(String path) throws Exception {
        File file = new File(path);
        try{
            // 打开文件
            Workbook book = Workbook.getWorkbook(file);
            int SHEET = book.getNumberOfSheets();
            System.out.println("SHEET = "+SHEET);
            int startCol = 1;
            for (int s = 0; s < SHEET; s++) {
                Sheet sheet = book.getSheet(s);
                int ROW = sheet.getRows();
                System.out.println("ROW = "+ROW);
                for (int r = 1; r < ROW; r++) {
                    System.out.println(r);
                    CTGData ctgData = new CTGData();
                    JudgeResult judgeResult = new JudgeResult();

                    if (!sheet.getCell(0, r).getContents().equals("")) {
                        ctgData.setPakageNumber(sheet.getCell(0, r).getContents());
                    }
                    if (!sheet.getCell(1, r).getContents().equals("")) {
                        ctgData.setCTG_number(sheet.getCell(1, r).getContents());
                    }
                    if (!sheet.getCell(2, r).getContents().equals("")) {
                        ctgData.setDeviceBrand(sheet.getCell(2, r).getContents());
                    }
                    if (!sheet.getCell(3, r).getContents().equals("")) {
                        ctgData.setDeviceEdition(sheet.getCell(3, r).getContents());
                    }
                    if (!sheet.getCell(4, r).getContents().equals("")) {
                        ctgData.setDeviceBatch(sheet.getCell(4, r).getContents());
                    }
                    if (!sheet.getCell(5, r).getContents().equals("")) {
                        ctgData.setTimes(Integer.parseInt(sheet.getCell(5, r).getContents()));
                    }
                    if (!sheet.getCell(6, r).getContents().equals("")) {
                        ctgData.setWatchTime(Integer.parseInt(sheet.getCell(6, r).getContents()));
                    }
                    if (!sheet.getCell(7, r).getContents().equals("")) {
                        ctgData.setGestational_Age(Double.parseDouble(sheet.getCell(7, r).getContents().replace('+', '.')));
                    }
                    if (!sheet.getCell(8, r).getContents().equals("")) {
                        ctgData.setCheckDate(TimestampUtil.CHECK_FMT.parse(sheet.getCell(8, r).getContents()));
                    }
                    if (!sheet.getCell(9, r).getContents().equals("")){
                        ctgData.setAge(Integer.parseInt(sheet.getCell(9, r).getContents()));
                    }
                    if (!sheet.getCell(10, r).getContents().equals("")){
                        ctgData.setLB(Integer.parseInt(sheet.getCell(10, r).getContents()));
                    }
                    if (!sheet.getCell(11, r).getContents().equals("")){
                        judgeResult.setLB(Integer.parseInt(sheet.getCell(11, r).getContents()));
                    }
                    if (!sheet.getCell(12, r).getContents().equals("")){
                        ctgData.setLTV(Integer.parseInt(sheet.getCell(12, r).getContents()));
                    }
                    if (!sheet.getCell(13, r).getContents().equals("")){
                        judgeResult.setLTV(Integer.parseInt(sheet.getCell(13, r).getContents()));
                    }
                    if (!sheet.getCell(14, r).getContents().equals("")){
                        ctgData.setPV(Integer.parseInt(sheet.getCell(14, r).getContents()));
                    }
                    if (!sheet.getCell(15, r).getContents().equals("")){
                        judgeResult.setPV(Integer.parseInt(sheet.getCell(15, r).getContents()));
                    }
                    if (!sheet.getCell(16, r).getContents().equals("")){
                        ctgData.setAC(Integer.parseInt(sheet.getCell(16, r).getContents()));
                    }
                    if (!sheet.getCell(17, r).getContents().equals("")){
                        judgeResult.setAC(Integer.parseInt(sheet.getCell(17, r).getContents()));
                    }
                    if (!sheet.getCell(18, r).getContents().equals("")){
                        ctgData.setDC(Integer.parseInt(sheet.getCell(18, r).getContents()));
                    }
                    if (!sheet.getCell(19, r).getContents().equals("")){
                        judgeResult.setDC(Integer.parseInt(sheet.getCell(19, r).getContents()));
                    }
                    if (!sheet.getCell(20, r).getContents().equals("")){
                        ctgData.setED(Integer.parseInt(sheet.getCell(20, r).getContents()));
                    }
                    if (!sheet.getCell(21, r).getContents().equals("")){
                        judgeResult.setED(Integer.parseInt(sheet.getCell(21, r).getContents()));
                    }
                    if (!sheet.getCell(22, r).getContents().equals("")){
                        ctgData.setLD(Integer.parseInt(sheet.getCell(22, r).getContents()));
                    }
                    if (!sheet.getCell(23, r).getContents().equals("")){
                        judgeResult.setLD(Integer.parseInt(sheet.getCell(23, r).getContents()));
                    }
                    if (!sheet.getCell(24, r).getContents().equals("")){
                        ctgData.setVD(Integer.parseInt(sheet.getCell(24, r).getContents()));
                    }
                    if (!sheet.getCell(25, r).getContents().equals("")){
                        judgeResult.setVD(Integer.parseInt(sheet.getCell(25, r).getContents()));
                    }
                    if (!sheet.getCell(26, r).getContents().equals("")){
                        ctgData.setDP(Integer.parseInt(sheet.getCell(26, r).getContents()));
                    }
                    if (!sheet.getCell(27, r).getContents().equals("")){
                        judgeResult.setDP(Integer.parseInt(sheet.getCell(27, r).getContents()));
                    }
                    if (!sheet.getCell(28, r).getContents().equals("")){
                        ctgData.setDL(Integer.parseInt(sheet.getCell(28, r).getContents()));
                    }
                    if (!sheet.getCell(29, r).getContents().equals("")){
                        judgeResult.setDL(Integer.parseInt(sheet.getCell(29, r).getContents()));
                    }
                    if (!sheet.getCell(30, r).getContents().equals("")){
                        ctgData.setDS(Integer.parseInt(sheet.getCell(30, r).getContents()));
                    }
                    if (!sheet.getCell(31, r).getContents().equals("")){
                        judgeResult.setDS(Integer.parseInt(sheet.getCell(31, r).getContents()));
                    }
                    if (!sheet.getCell(32, r).getContents().equals("")){
                        ctgData.setFM(Integer.parseInt(sheet.getCell(32, r).getContents()));
                    }
                    if (!sheet.getCell(33, r).getContents().equals("")){
                        judgeResult.setFM(Integer.parseInt(sheet.getCell(33, r).getContents()));
                    }
                    if (!sheet.getCell(34, r).getContents().equals("")){
                        ctgData.setUC(Integer.parseInt(sheet.getCell(34, r).getContents()));
                    }
                    if (!sheet.getCell(35, r).getContents().equals("")){
                        judgeResult.setUC(Integer.parseInt(sheet.getCell(35, r).getContents()));
                    }
                    if (!sheet.getCell(36, r).getContents().equals("")){
                        ctgData.setAI(Integer.parseInt(sheet.getCell(36, r).getContents()));
                    }
                    if (!sheet.getCell(37, r).getContents().equals("")){
                        judgeResult.setAI(Integer.parseInt(sheet.getCell(37, r).getContents()));
                    }
                    if (!sheet.getCell(38, r).getContents().equals("")){
                        ctgData.setAA(Integer.parseInt(sheet.getCell(38, r).getContents()));
                    }
                    if (!sheet.getCell(39, r).getContents().equals("")){
                        judgeResult.setAA(Integer.parseInt(sheet.getCell(39, r).getContents()));
                    }
                    if (!sheet.getCell(40, r).getContents().equals("")){
                        ctgData.setSTV(Double.parseDouble(sheet.getCell(40, r).getContents()));
                    }
                    if (!sheet.getCell(41, r).getContents().equals("")){
                        ctgData.setHVT(Integer.parseInt(sheet.getCell(41, r).getContents()));
                    }
                    if (!sheet.getCell(42, r).getContents().equals("")){
                        ctgData.setLVT(Integer.parseInt(sheet.getCell(42, r).getContents()));
                    }
                    if (!sheet.getCell(43, r).getContents().equals("")){
                        ctgData.setSpeedChangingTime(Integer.parseInt(sheet.getCell(43, r).getContents()));
                    }
                    if (!sheet.getCell(44, r).getContents().equals("")){
                        ctgData.setLostRate(Integer.parseInt(sheet.getCell(44, r).getContents()));
                    }
                    //45

                    //46

                    //47

                    if (!sheet.getCell(45, r).getContents().equals("")){
                        ctgData.setExpertReadBegin(Integer.parseInt(sheet.getCell(45, r).getContents()));
                    }
                    if (!sheet.getCell(46, r).getContents().equals("")){
                        ctgData.setExpertReadEnd(Integer.parseInt(sheet.getCell(46, r).getContents()));
                    }
                    if (!sheet.getCell(47, r).getContents().equals("")){
                        ctgData.setNST(JudgeUtil.getJudgeIntCodeByStr(sheet.getCell(47, r).getContents()));
                    }
                    if (!sheet.getCell(48, r).getContents().equals("")){
                        judgeResult.setNST(JudgeUtil.getJudgeIntCodeByStr(sheet.getCell(48, r).getContents()));
                    }
                    if (!sheet.getCell(49, r).getContents().equals("")){
                        ctgData.setJudgeDate(TimestampUtil.JUDGE_FMT.parse(sheet.getCell(49, r).getContents()));
                    }

                    // 53
                    if (!sheet.getCell(50, r).getContents().equals("")&&!expertRepository.findByExpertID(sheet.getCell(50, r).getContents()).isEmpty()){
                        Expert expert = new Expert();
                        expert.setExpertID(sheet.getCell(50, r).getContents());

                        // 54
                        if (!sheet.getCell(51, r).getContents().equals("")){
                            expert.setGrade(Integer.parseInt(sheet.getCell(51, r).getContents()));
                        }
                        expertRepository.save(expert);
                    }
                    //55
                    if (!sheet.getCell(46, r).getContents().equals("")&&!sheet.getCell(45, r).getContents().equals("")){
                        judgeResult.setCostTime(Integer.parseInt(sheet.getCell(46, r).getContents()) - Integer.parseInt(sheet.getCell(45, r).getContents()));
                    }
                    if (!sheet.getCell(50, r).getContents().equals("")){
                        judgeResult.setExpert_ID(sheet.getCell(50, r).getContents());
                    }
                    if (!sheet.getCell(52, r).getContents().equals("")){
                        judgeResult.setNotes(sheet.getCell(52, r).getContents());
                    }

                    // System.out.println(file.getCanonicalPath());
                    String fileName = path.substring(file.getCanonicalPath().lastIndexOf("\\")+1,path.length());
                    ctgData.setFileName(fileName);
                    // System.out.println(fileName);
                    // System.out.println(ctgData.toString());
                    ctgData = ctgDataRepository.save(ctgData);
                    judgeResult.setCTG_ID(ctgData.getId());
                    judgeResultRepository.save(judgeResult);
                    // break;

                }
            }
            book.close();
            return "ok";
        }catch (Exception e){
            throw e;
        }
    }
}
