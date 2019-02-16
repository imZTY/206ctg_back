package io.renren.modules.CTG.service;

import io.renren.modules.CTG.dto.JudgeResultDTO;
import io.renren.modules.CTG.dto.LineDataDTO;
import io.renren.modules.CTG.dto.NewCTGDataDTO;
import io.renren.modules.CTG.entity.CTGData;
import io.renren.modules.CTG.entity.Expert;
import io.renren.modules.CTG.entity.JudgeResult;
import io.renren.modules.CTG.entity.PageEntity;
import io.renren.modules.CTG.repository.CTGDataRepository;
import io.renren.modules.CTG.repository.ExpertRepository;
import io.renren.modules.CTG.repository.JudgeResultRepository;
import io.renren.modules.CTG.utils.FileUtil;
import io.renren.modules.CTG.utils.JudgeUtil;
import io.renren.modules.CTG.utils.TimestampConstant;
import io.renren.modules.sys.dao.ctg.ICTGDataDao;
import io.renren.modules.sys.dao.ctg.IJudgeResultDao;
import jxl.Sheet;
import jxl.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.io.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static io.renren.common.utils.ShiroUtils.getUserId;

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

    @Autowired
    private ICTGDataDao ctgDataDao;

    @Autowired
    private IJudgeResultDao judgeResultDao;

//    @Autowired
//    private SimpMessagingTemplate messagingTemplate; // WebSocket工具类

    public List<Expert> getExpertList(){
        return expertRepository.findAll();
    }

    /**
     * 将文件导入数据库
     * @param path 文件的完整路径
     * @param isSkip 遇到错误是否跳过
     * @param upTime 上传时间戳
     * @param lastMsg 前面环节迭代下来的反馈日志
     * @return
     * @throws Exception
     */
    public String importDataFromExcelFile(String path, boolean isSkip, long upTime, String lastMsg) throws Exception {
        StringBuffer rtHead = new StringBuffer();
        StringBuffer rtTail = new StringBuffer();
        int errorHappenTime = 1;
        int lastErrorHappenTime = errorHappenTime;
        Long userId = getUserId();
        File file = new File(path);
        try{
            // 打开文件
            Workbook book = Workbook.getWorkbook(file);
            int SHEET = book.getNumberOfSheets();
            System.out.println("SHEET = "+SHEET);
            int startCol = 1;
            // s:=表序号
            for (int s = 0; s < SHEET; s++) {
                Sheet sheet = book.getSheet(s);
                int ROW = sheet.getRows();
                System.out.println("ROW = " + ROW);

                boolean isOk = true;
                try {
                    Integer.parseInt(sheet.getCell(5, 1).getContents());
                    Integer.parseInt(sheet.getCell(6, 1).getContents());
                    Integer.parseInt(sheet.getCell(9, 1).getContents());
                    Integer.parseInt(sheet.getCell(10, 1).getContents());
                    Integer.parseInt(sheet.getCell(11, 1).getContents());
                    Integer.parseInt(sheet.getCell(12, 1).getContents());
                    Integer.parseInt(sheet.getCell(13, 1).getContents());
                    Integer.parseInt(sheet.getCell(14, 1).getContents());
                    Integer.parseInt(sheet.getCell(15, 1).getContents());
                    Integer.parseInt(sheet.getCell(16, 1).getContents());
                    Integer.parseInt(sheet.getCell(17, 1).getContents());
                    Integer.parseInt(sheet.getCell(18, 1).getContents());
                    Integer.parseInt(sheet.getCell(19, 1).getContents());
                    Integer.parseInt(sheet.getCell(20, 1).getContents());
                    Integer.parseInt(sheet.getCell(21, 1).getContents());
                    Integer.parseInt(sheet.getCell(22, 1).getContents());
                    Integer.parseInt(sheet.getCell(23, 1).getContents());
                    Integer.parseInt(sheet.getCell(24, 1).getContents());
                    Integer.parseInt(sheet.getCell(25, 1).getContents());
                    Integer.parseInt(sheet.getCell(26, 1).getContents());
                    Integer.parseInt(sheet.getCell(27, 1).getContents());
                    Integer.parseInt(sheet.getCell(28, 1).getContents());
                    Integer.parseInt(sheet.getCell(29, 1).getContents());
                    Integer.parseInt(sheet.getCell(30, 1).getContents());
                    Integer.parseInt(sheet.getCell(31, 1).getContents());
                    Integer.parseInt(sheet.getCell(32, 1).getContents());
                    Integer.parseInt(sheet.getCell(33, 1).getContents());
                    Integer.parseInt(sheet.getCell(34, 1).getContents());
                    Integer.parseInt(sheet.getCell(35, 1).getContents());
                    Integer.parseInt(sheet.getCell(36, 1).getContents());
                    Integer.parseInt(sheet.getCell(37, 1).getContents());
                    Integer.parseInt(sheet.getCell(38, 1).getContents());
                    Integer.parseInt(sheet.getCell(39, 1).getContents());
                    Integer.parseInt(sheet.getCell(41, 1).getContents());
                    Integer.parseInt(sheet.getCell(42, 1).getContents());
                    Integer.parseInt(sheet.getCell(43, 1).getContents());
                    Integer.parseInt(sheet.getCell(44, 1).getContents());
                    Integer.parseInt(sheet.getCell(45, 1).getContents());
                    Integer.parseInt(sheet.getCell(46, 1).getContents());
                    Integer.parseInt(sheet.getCell(47, 1).getContents());
                    Integer.parseInt(sheet.getCell(48, 1).getContents());
                    Integer.parseInt(sheet.getCell(49, 1).getContents());
                    Integer.parseInt(sheet.getCell(50, 1).getContents());
                    Integer.parseInt(sheet.getCell(51, 1).getContents());
                    Integer.parseInt(sheet.getCell(52, 1).getContents());
                    Integer.parseInt(sheet.getCell(53, 1).getContents());
                    Integer.parseInt(sheet.getCell(54, 1).getContents());
                    Integer.parseInt(sheet.getCell(55, 1).getContents());
                    Integer.parseInt(sheet.getCell(56, 1).getContents());
                    Integer.parseInt(sheet.getCell(60, 1).getContents());
                    Integer.parseInt(sheet.getCell(61, 1).getContents());
                    Double.parseDouble(sheet.getCell(7, 1).getContents());
                    Double.parseDouble(sheet.getCell(40, 1).getContents());
                    TimestampConstant.CHECK_FMT.parse(sheet.getCell(8, 1).getContents());
                    TimestampConstant.JUDGE_FMT.parse(sheet.getCell(59, 1).getContents());
                } catch (Exception e) {
                    e.printStackTrace();
                    isOk = false;
                }

                if (isOk) {
                    // r:=行号
                    for (int r = 1; r < ROW; r++) {
                        try {
                            System.out.println(r);
                            CTGData ctgData = new CTGData();
                            ctgData.setUpTime(upTime);
                            JudgeResult judgeResult = new JudgeResult();

                            // 包号
                            if (!sheet.getCell(0, r).getContents().equals("")) {
                                ctgData.setPakageNumber(sheet.getCell(0, r).getContents());
                            } else {
                                rtTail.append(errorHappenTime + ". 第 " + (r + 1) + " 行发现包号为空值;\n");
                                errorHappenTime++;
                            }

                            // 编号
                            if (!sheet.getCell(1, r).getContents().equals("")) {
                                ctgData.setCTG_number(sheet.getCell(1, r).getContents());
                            } else {
                                rtTail.append(errorHappenTime + ". 第 " + (r + 1) + " 行发现编号为空值;\n");
                                errorHappenTime++;
                            }

                            // brand测量仪器品牌
                            if (!sheet.getCell(2, r).getContents().equals("")) {
                                ctgData.setDeviceBrand(sheet.getCell(2, r).getContents());
                            }

                            // model测量仪器型号
                            if (!sheet.getCell(3, r).getContents().equals("")) {
                                ctgData.setDeviceEdition(sheet.getCell(3, r).getContents());
                            }

                            // batch测量仪器批次
                            if (!sheet.getCell(4, r).getContents().equals("")) {
                                ctgData.setDeviceBatch(sheet.getCell(4, r).getContents());
                            }

                            // 次数
                            if (!sheet.getCell(5, r).getContents().equals("")) {
                                ctgData.setTimes(Integer.parseInt(sheet.getCell(5, r).getContents()));
                            } else {
                                rtTail.append(errorHappenTime + ". 第 " + (r + 1) + " 行发现次数为空值;\n");
                                errorHappenTime++;
                            }

                            // 监护时长
                            if (!sheet.getCell(6, r).getContents().equals("")) {
                                ctgData.setWatchTime(Integer.parseInt(sheet.getCell(6, r).getContents()));
                            } else {
                                rtTail.append(errorHappenTime + ". 第 " + (r + 1) + " 行发现监护时长为空值;\n");
                                errorHappenTime++;
                            }

                            // 孕周
                            if (!sheet.getCell(7, r).getContents().equals("")) {
                                double gestationalAge = Double.parseDouble(sheet.getCell(7, r).getContents().replace('+', '.'));
                                if (gestationalAge < 28.0){
                                    rtTail.append(errorHappenTime + ". 第 " + (r + 1) + " 行发现孕周小于 28，跳过此行数据不予处理;\n");
                                    errorHappenTime++;
                                    continue;
                                }
                                ctgData.setGestationalAge(gestationalAge);
                            } else {
                                rtTail.append(errorHappenTime + ". 第 " + (r + 1) + " 行发现孕周为空值，跳过此行数据不予处理;\n");
                                errorHappenTime++;
                                continue;
                            }

                            // 检查时间
                            if (!sheet.getCell(8, r).getContents().equals("")) {
                                ctgData.setCheckDate(TimestampConstant.CHECK_FMT.parse(sheet.getCell(8, r).getContents()));
                            } else {
                                rtTail.append(errorHappenTime + ". 第 " + (r + 1) + " 行发现检查时间为空值;\n");
                                errorHappenTime++;
                            }

                            // 年龄
                            if (!sheet.getCell(9, r).getContents().equals("")) {
                                ctgData.setAge(Integer.parseInt(sheet.getCell(9, r).getContents()));
                            } else {
                                rtTail.append(errorHappenTime + ". 第 " + (r + 1) + " 行发现年龄为空值;\n");
                                errorHappenTime++;
                            }

                            // 基线
                            if (!sheet.getCell(10, r).getContents().equals("")) {
                                ctgData.setBaseLine(Integer.parseInt(sheet.getCell(10, r).getContents()));
                            } else {
                                rtTail.append(errorHappenTime + ". 第 " + (r + 1) + " 行发现基线为空值;\n");
                                errorHappenTime++;
                            }

                            // 基线 M
                            if (!sheet.getCell(11, r).getContents().equals("")) {
                                judgeResult.setBaseLine(Integer.parseInt(sheet.getCell(11, r).getContents()));
                            } else {
                                rtTail.append(errorHappenTime + ". 第 " + (r + 1) + " 行发现基线 M为空值;\n");
                                errorHappenTime++;
                            }

                            // 长变异
                            if (!sheet.getCell(12, r).getContents().equals("")) {
                                ctgData.setFHRVAR(Integer.parseInt(sheet.getCell(12, r).getContents()));
                            } else {
                                rtTail.append(errorHappenTime + ". 第 " + (r + 1) + " 行发现长变异为空值;\n");
                                errorHappenTime++;
                            }

                            // 长变异 M
                            if (!sheet.getCell(13, r).getContents().equals("")) {
                                judgeResult.setFHRVAR(Integer.parseInt(sheet.getCell(13, r).getContents()));
                            } else {
                                rtTail.append(errorHappenTime + ". 第 " + (r + 1) + " 行发现长变异 M为空值;\n");
                                errorHappenTime++;
                            }

                            // 周期变异
                            if (!sheet.getCell(14, r).getContents().equals("")) {
                                ctgData.setFHRCYCLE(Integer.parseInt(sheet.getCell(14, r).getContents()));
                            } else {
                                rtTail.append(errorHappenTime + ". 第 " + (r + 1) + " 行发现周期变异为空值;\n");
                                errorHappenTime++;
                            }

                            // 周期变异 M
                            if (!sheet.getCell(15, r).getContents().equals("")) {
                                judgeResult.setFHRCYCLE(Integer.parseInt(sheet.getCell(15, r).getContents()));
                            } else {
                                rtTail.append(errorHappenTime + ". 第 " + (r + 1) + " 行发现周期变异 M为空值;\n");
                                errorHappenTime++;
                            }

                            // 加速
                            if (!sheet.getCell(16, r).getContents().equals("")) {
                                ctgData.setFHRFAST(Integer.parseInt(sheet.getCell(16, r).getContents()));
                            } else {
                                rtTail.append(errorHappenTime + ". 第 " + (r + 1) + " 行发现加速为空值;\n");
                                errorHappenTime++;
                            }

                            // 加速 M
                            if (!sheet.getCell(17, r).getContents().equals("")) {
                                judgeResult.setFHRFAST(Integer.parseInt(sheet.getCell(17, r).getContents()));
                            } else {
                                rtTail.append(errorHappenTime + ". 第 " + (r + 1) + " 行发现加速 M为空值;\n");
                                errorHappenTime++;
                            }

                            // 减速
                            if (!sheet.getCell(18, r).getContents().equals("")) {
                                ctgData.setFHSLOW(Integer.parseInt(sheet.getCell(18, r).getContents()));
                            } else {
                                rtTail.append(errorHappenTime + ". 第 " + (r + 1) + " 行发现减速为空值;\n");
                                errorHappenTime++;
                            }

                            // 减速 M
                            if (!sheet.getCell(19, r).getContents().equals("")) {
                                judgeResult.setFHSLOW(Integer.parseInt(sheet.getCell(19, r).getContents()));
                            } else {
                                rtTail.append(errorHappenTime + ". 第 " + (r + 1) + " 行发现减速 M为空值;\n");
                                errorHappenTime++;
                            }

                            // 早减
                            if (!sheet.getCell(20, r).getContents().equals("")) {
                                ctgData.setED(Integer.parseInt(sheet.getCell(20, r).getContents()));
                            } else {
                                rtTail.append(errorHappenTime + ". 第 " + (r + 1) + " 行发现早减为空值;\n");
                                errorHappenTime++;
                            }

                            // 早减 M
                            if (!sheet.getCell(21, r).getContents().equals("")) {
                                judgeResult.setED(Integer.parseInt(sheet.getCell(21, r).getContents()));
                            } else {
                                rtTail.append(errorHappenTime + ". 第 " + (r + 1) + " 行发现早减 M为空值;\n");
                                errorHappenTime++;
                            }

                            // 晚减
                            if (!sheet.getCell(22, r).getContents().equals("")) {
                                ctgData.setLD(Integer.parseInt(sheet.getCell(22, r).getContents()));
                            } else {
                                rtTail.append(errorHappenTime + ". 第 " + (r + 1) + " 行发现晚减为空值;\n");
                                errorHappenTime++;
                            }

                            // 晚减 M
                            if (!sheet.getCell(23, r).getContents().equals("")) {
                                judgeResult.setLD(Integer.parseInt(sheet.getCell(23, r).getContents()));
                            } else {
                                rtTail.append(errorHappenTime + ". 第 " + (r + 1) + " 行发现晚减 M为空值;\n");
                                errorHappenTime++;
                            }

                            // 变异减速
                            if (!sheet.getCell(24, r).getContents().equals("")) {
                                ctgData.setVD(Integer.parseInt(sheet.getCell(24, r).getContents()));
                            } else {
                                rtTail.append(errorHappenTime + ". 第 " + (r + 1) + " 行发现变异减速为空值;\n");
                                errorHappenTime++;
                            }

                            // 变异减速 M
                            if (!sheet.getCell(25, r).getContents().equals("")) {
                                judgeResult.setVD(Integer.parseInt(sheet.getCell(25, r).getContents()));
                            } else {
                                rtTail.append(errorHappenTime + ". 第 " + (r + 1) + " 行发现变异减速 M为空值;\n");
                                errorHappenTime++;
                            }

                            // 延长减速
                            if (!sheet.getCell(26, r).getContents().equals("")) {
                                ctgData.setDP(Integer.parseInt(sheet.getCell(26, r).getContents()));
                            } else {
                                rtTail.append(errorHappenTime + ". 第 " + (r + 1) + " 行发现延长减速为空值;\n");
                                errorHappenTime++;
                            }

                            // 延长减速 M
                            if (!sheet.getCell(27, r).getContents().equals("")) {
                                judgeResult.setDP(Integer.parseInt(sheet.getCell(27, r).getContents()));
                            } else {
                                rtTail.append(errorHappenTime + ". 第 " + (r + 1) + " 行发现延长减速 M为空值;\n");
                                errorHappenTime++;
                            }

                            // 轻度减速
                            if (!sheet.getCell(28, r).getContents().equals("")) {
                                ctgData.setDL(Integer.parseInt(sheet.getCell(28, r).getContents()));
                            } else {
                                rtTail.append(errorHappenTime + ". 第 " + (r + 1) + " 行发现轻度减速为空值;\n");
                                errorHappenTime++;
                            }

                            // 轻度减速 M
                            if (!sheet.getCell(29, r).getContents().equals("")) {
                                judgeResult.setDL(Integer.parseInt(sheet.getCell(29, r).getContents()));
                            } else {
                                rtTail.append(errorHappenTime + ". 第 " + (r + 1) + " 行发现轻度减速 M为空值;\n");
                                errorHappenTime++;
                            }

                            // 重度减速
                            if (!sheet.getCell(30, r).getContents().equals("")) {
                                ctgData.setDS(Integer.parseInt(sheet.getCell(30, r).getContents()));
                            } else {
                                rtTail.append(errorHappenTime + ". 第 " + (r + 1) + " 行发现重度减速为空值;\n");
                                errorHappenTime++;
                            }

                            // 重度减速 M
                            if (!sheet.getCell(31, r).getContents().equals("")) {
                                judgeResult.setDS(Integer.parseInt(sheet.getCell(31, r).getContents()));
                            } else {
                                rtTail.append(errorHappenTime + ". 第 " + (r + 1) + " 行发现重度减速 M为空值;\n");
                                errorHappenTime++;
                            }

                            // 胎动
                            if (!sheet.getCell(32, r).getContents().equals("")) {
                                ctgData.setFMOVE(Integer.parseInt(sheet.getCell(32, r).getContents()));
                            } else {
                                rtTail.append(errorHappenTime + ". 第 " + (r + 1) + " 行发现胎动为空值;\n");
                                errorHappenTime++;
                            }

                            // 胎动 M
                            if (!sheet.getCell(33, r).getContents().equals("")) {
                                judgeResult.setFMOVE(Integer.parseInt(sheet.getCell(33, r).getContents()));
                            } else {
                                rtTail.append(errorHappenTime + ". 第 " + (r + 1) + " 行发现胎动 M为空值;\n");
                                errorHappenTime++;
                            }

                            // 宫缩次数
                            if (!sheet.getCell(34, r).getContents().equals("")) {
                                ctgData.setUCTIMES(Integer.parseInt(sheet.getCell(34, r).getContents()));
                            } else {
                                rtTail.append(errorHappenTime + ". 第 " + (r + 1) + " 行发现宫缩次数为空值;\n");
                                errorHappenTime++;
                            }

                            // 宫缩次数 M
                            if (!sheet.getCell(35, r).getContents().equals("")) {
                                judgeResult.setUCTIMES(Integer.parseInt(sheet.getCell(35, r).getContents()));
                            } else {
                                rtTail.append(errorHappenTime + ". 第 " + (r + 1) + " 行发现宫缩次数 M为空值;\n");
                                errorHappenTime++;
                            }

                            // 加速时间(秒)
                            if (!sheet.getCell(36, r).getContents().equals("")) {
                                ctgData.setFASTTIME(Integer.parseInt(sheet.getCell(36, r).getContents()));
                            } else {
                                rtTail.append(errorHappenTime + ". 第 " + (r + 1) + " 行发现加速时间(秒)为空值;\n");
                                errorHappenTime++;
                            }

                            // 加速时间(秒) M
                            if (!sheet.getCell(37, r).getContents().equals("")) {
                                judgeResult.setFASTTIME(Integer.parseInt(sheet.getCell(37, r).getContents()));
                            } else {
                                rtTail.append(errorHappenTime + ". 第 " + (r + 1) + " 行发现加速时间(秒) M为空值;\n");
                                errorHappenTime++;
                            }

                            // 加速幅度(bpm)
                            if (!sheet.getCell(38, r).getContents().equals("")) {
                                ctgData.setFASTVALUE(Integer.parseInt(sheet.getCell(38, r).getContents()));
                            } else {
                                rtTail.append(errorHappenTime + ". 第 " + (r + 1) + " 行发现加速幅度(bpm)为空值;\n");
                                errorHappenTime++;
                            }

                            // 加速幅度(bpm) M
                            if (!sheet.getCell(39, r).getContents().equals("")) {
                                judgeResult.setFASTVALUE(Integer.parseInt(sheet.getCell(39, r).getContents()));
                            } else {
                                rtTail.append(errorHappenTime + ". 第 " + (r + 1) + " 行发现加速幅度(bpm) M为空值;\n");
                                errorHappenTime++;
                            }

                            // 短变异(ms)
                            if (!sheet.getCell(40, r).getContents().equals("")) {
                                ctgData.setSTV(Double.parseDouble(sheet.getCell(40, r).getContents()));
                            } else {
                                rtTail.append(errorHappenTime + ". 第 " + (r + 1) + " 行发现短变异(ms)为空值;\n");
                                errorHappenTime++;
                            }

                            // 高变异时间
                            if (!sheet.getCell(41, r).getContents().equals("")) {
                                ctgData.setHIGHLTV(Integer.parseInt(sheet.getCell(41, r).getContents()));
                            } else {
                                rtTail.append(errorHappenTime + ". 第 " + (r + 1) + " 行发现高变异时间为空值;\n");
                                errorHappenTime++;
                            }

                            // 低变异时间
                            if (!sheet.getCell(42, r).getContents().equals("")) {
                                ctgData.setLOWLTV(Integer.parseInt(sheet.getCell(42, r).getContents()));
                            } else {
                                rtTail.append(errorHappenTime + ". 第 " + (r + 1) + " 行发现低变异时间为空值;\n");
                                errorHappenTime++;
                            }

                            // 有加减速时间
                            if (!sheet.getCell(43, r).getContents().equals("")) {
                                ctgData.setHAVEACCDEC(Integer.parseInt(sheet.getCell(43, r).getContents()));
                            } else {
                                rtTail.append(errorHappenTime + ". 第 " + (r + 1) + " 行发现有加减速时间为空值;\n");
                                errorHappenTime++;
                            }

                            // 丢失率(%)
                            if (!sheet.getCell(44, r).getContents().equals("")) {
                                ctgData.setLostRate(Integer.parseInt(sheet.getCell(44, r).getContents()));
                            } else {
                                rtTail.append(errorHappenTime + ". 第 " + (r + 1) + " 行发现丢失率(%)为空值;\n");
                                errorHappenTime++;
                            }

                            //    宫缩强度(%)	45
                            if (!sheet.getCell(45, r).getContents().equals("")) {
                                ctgData.setUCSTRONG(Integer.parseInt(sheet.getCell(45, r).getContents()));
                            } else {
                                rtTail.append(errorHappenTime + ". 第 " + (r + 1) + " 行发现宫缩强度(%)为空值;\n");
                                errorHappenTime++;
                            }

                            //    宫缩间隔时间(秒)	46
                            if (!sheet.getCell(46, r).getContents().equals("")) {
                                ctgData.setUCNEXT(Integer.parseInt(sheet.getCell(46, r).getContents()));
                            } else {
                                rtTail.append(errorHappenTime + ". 第 " + (r + 1) + " 行发现宫缩间隔时间(秒)为空值;\n");
                                errorHappenTime++;
                            }

                            //    宫缩持续时间(秒)	47
                            if (!sheet.getCell(47, r).getContents().equals("")) {
                                ctgData.setUCKTIME(Integer.parseInt(sheet.getCell(47, r).getContents()));
                            } else {
                                rtTail.append(errorHappenTime + ". 第 " + (r + 1) + " 行发现宫缩持续时间(秒)为空值;\n");
                                errorHappenTime++;
                            }

                            //    变异减速时间(秒)	48
                            if (!sheet.getCell(48, r).getContents().equals("")) {
                                ctgData.setVDTIME(Integer.parseInt(sheet.getCell(48, r).getContents()));
                            } else {
                                rtTail.append(errorHappenTime + ". 第 " + (r + 1) + " 行发现变异减速时间(秒)为空值;\n");
                                errorHappenTime++;
                            }

                            //    胎心率评价	49
                            if (!sheet.getCell(49, r).getContents().equals("")) {
                                ctgData.setFRH_EVALUATION(Integer.parseInt(sheet.getCell(49, r).getContents()));
                            } else {
                                rtTail.append(errorHappenTime + ". 第 " + (r + 1) + " 行发现胎心率评价为空值;\n");
                                errorHappenTime++;
                            }

                            //    长变异评价	50
                            if (!sheet.getCell(50, r).getContents().equals("")) {
                                ctgData.setVAR_EVALUATION(Integer.parseInt(sheet.getCell(50, r).getContents()));
                            } else {
                                rtTail.append(errorHappenTime + ". 第 " + (r + 1) + " 行发现长变异评价为空值;\n");
                                errorHappenTime++;
                            }

                            //    减速评价	51
                            if (!sheet.getCell(51, r).getContents().equals("")) {
                                ctgData.setFHSLOW_EVALUATION(Integer.parseInt(sheet.getCell(51, r).getContents()));
                            } else {
                                rtTail.append(errorHappenTime + ". 第 " + (r + 1) + " 行发现减速评价为空值;\n");
                                errorHappenTime++;
                            }

                            //    足月加速评价	52
                            if (!sheet.getCell(52, r).getContents().equals("")) {
                                ctgData.setFHRFAST_EVALUATION_1(Integer.parseInt(sheet.getCell(52, r).getContents()));
                            } else {
                                rtTail.append(errorHappenTime + ". 第 " + (r + 1) + " 行发现足月加速评价为空值;\n");
                                errorHappenTime++;
                            }

                            //    不足月加速评价	53
                            if (!sheet.getCell(53, r).getContents().equals("")) {
                                ctgData.setFHRFAST_EVALUATION_0(Integer.parseInt(sheet.getCell(53, r).getContents()));
                            } else {
                                rtTail.append(errorHappenTime + ". 第 " + (r + 1) + " 行发现不足月加速评价为空值;\n");
                                errorHappenTime++;
                            }

                            //    NST处理评价	54
                            if (!sheet.getCell(54, r).getContents().equals("")) {
                                ctgData.setNST_EVALUATION(Integer.parseInt(sheet.getCell(54, r).getContents()));
                            } else {
                                rtTail.append(errorHappenTime + ". 第 " + (r + 1) + " 行发现NST处理评价为空值;\n");
                                errorHappenTime++;
                            }

                            //    分析开始(分钟)	55
                            if (!sheet.getCell(55, r).getContents().equals("")) {
                                ctgData.setExpertReadBegin(Integer.parseInt(sheet.getCell(55, r).getContents()));
                            } else {
                                rtTail.append(errorHappenTime + ". 第 " + (r + 1) + " 行发现分析开始(分钟)为空值;\n");
                                errorHappenTime++;
                            }

                            //    分析结束(分钟)	56
                            if (!sheet.getCell(56, r).getContents().equals("")) {
                                ctgData.setExpertReadEnd(Integer.parseInt(sheet.getCell(56, r).getContents()));
                            } else {
                                rtTail.append(errorHappenTime + ". 第 " + (r + 1) + " 行发现分析结束(分钟)为空值;\n");
                                errorHappenTime++;
                            }

                            //    评价	57
                            if (!sheet.getCell(57, r).getContents().equals("")) {
                                ctgData.setNST(JudgeUtil.getJudgeIntCodeByStr(sheet.getCell(57, r).getContents()));
                            } else {
                                rtTail.append(errorHappenTime + ". 第 " + (r + 1) + " 行发现评价为空值;\n");
                                errorHappenTime++;
                            }

                            //    评价M	58
                            if (!sheet.getCell(58, r).getContents().equals("")) {
                                judgeResult.setNST(JudgeUtil.getJudgeIntCodeByStr(sheet.getCell(58, r).getContents()));
                            } else {
                                rtTail.append(errorHappenTime + ". 第 " + (r + 1) + " 行发现评价M为空值，跳过此行数据不予处理;\n");
                                errorHappenTime++;
                                continue;
                            }

                            //    判读时间	59
                            if (!sheet.getCell(59, r).getContents().equals("")) {
                                judgeResult.setJudgeDate(TimestampConstant.JUDGE_FMT.parse(sheet.getCell(59, r).getContents()));
                                ctgData.setJudgeDate(TimestampConstant.JUDGE_FMT.parse(sheet.getCell(59, r).getContents()));
                            } else {
                                rtTail.append(errorHappenTime + ". 第 " + (r + 1) + " 行发现判读时间为空值;\n");
                                errorHappenTime++;
                            }

                            //    医师	60
                            if (!sheet.getCell(60, r).getContents().equals("")) {
                                if (expertRepository.findByExpertId(Integer.parseInt(sheet.getCell(60, r).getContents())).isEmpty()) {
                                    Expert expert = new Expert();
                                    expert.setExpertId(Integer.parseInt(sheet.getCell(60, r).getContents()));

                                    //    医师级别	61
                                    if (!sheet.getCell(61, r).getContents().equals("")) {
                                        expert.setGrade(Integer.parseInt(sheet.getCell(61, r).getContents()));
                                    } else {
                                        rtTail.append(errorHappenTime + ". 第 " + (r + 1) + " 行发现医师级别为空值;\n");
                                        errorHappenTime++;
                                    }
                                    expertRepository.save(expert);

                                    judgeResult.setExpertId(expert.getExpertId());
                                } else {
                                    judgeResult.setExpertId(Integer.parseInt(sheet.getCell(60, r).getContents()));
                                }
                            } else {
                                rtTail.append(errorHappenTime + ". 第 " + (r + 1) + " 行发现医师ID为空值;\n");
                                errorHappenTime++;
                            }

                            //    备注	62
                            if (!sheet.getCell(62, r).getContents().equals("")) {
                                judgeResult.setNotes(sheet.getCell(62, r).getContents().toString());
                            }

                            // System.out.println(file.getCanonicalPath());
                            // String fileName = path.substring(file.getCanonicalPath().lastIndexOf("\\") + 1, path.length());
                            String fileName = file.getName();
                            ctgData.setFileName(fileName);
                            ctgData.setUpTime(upTime);

                            // TODO 这个code后面可以作为自设的统一新规则id
                            ctgData.setCtgCode(ctgData.getPakageNumber() + "_" + ctgData.getCTG_number());
                            judgeResult.setCtgCode(ctgData.getCtgCode());

                            // System.out.println(fileName);
                            // System.out.println(ctgData.toString());
                            ctgData = ctgDataRepository.save(ctgData);
                            judgeResult.setCtgId(ctgData.getId());
                            judgeResultRepository.save(judgeResult);
                        } catch (Exception e) {
                            e.printStackTrace();
                            rtTail.append(errorHappenTime + ". 第 " + (r + 1) + " 行出现错误：" + e.getMessage() + ";\n");
                            errorHappenTime++;
                            if (isSkip) {
                                continue;
                            } else {
                                break;
                            }
                        }

                        // 只有出现新错误的时候才会更新反馈信息
                        if (lastErrorHappenTime != errorHappenTime) {
                            lastErrorHappenTime = errorHappenTime;

                            String refresh = "{\"isOver\":false,\"message\":\"" + lastMsg
                                    +"导入 " + file.getName() + " 出现错误\n" + rtTail.toString()
                                    +"\"}";
                            // this.messagingTemplate.convertAndSendToUser("" + userId, "/status", refresh);
                        }
                    }

                }else{
                    rtTail.append(errorHappenTime + ". 第 " + (s + 1) + " 个sheet表的格式有问题请检查，就此停止导入工作;\n");
                    errorHappenTime++;
                    break;
                }
            }
            ctgDataDao.deleteRepeat();
            judgeResultDao.deleteRepeat();
            book.close();
            if (errorHappenTime == 1) {
                rtHead.append("导入 " + file.getName() + " 成功\n").append(rtTail.toString());
                return rtHead.toString();
            } else {
                rtHead.append("导入 " + file.getName() + " 出现错误\n").append(rtTail.toString());
                return rtHead.toString();
            }
        }catch (Exception ee){
            throw ee;
        }
    }

    /**
     * 新接口获取ctg数据同时顺带其他——judge和line
     * @param pageEntity
     * @return
     */
    public List<NewCTGDataDTO> ctgDataPageable(PageEntity pageEntity){
        pageEntity.setPageStart( (pageEntity.getPage()-1)*pageEntity.getRows() );

        List<CTGData> ctgDataList = ctgDataDao.getByPage(pageEntity);
        List<NewCTGDataDTO> rt = new ArrayList<>(ctgDataList.size());
        for (CTGData ctgData:
             ctgDataList) {
            List<JudgeResult> judgeResultList = judgeResultRepository.findAllByCtgCode(ctgData.getCtgCode());
            List<JudgeResultDTO> judgeResultDTOList = new ArrayList<JudgeResultDTO>(judgeResultList.size());
            for (JudgeResult judgeResult:
                 judgeResultList) {
                Expert expert = expertRepository.findByExpertId(judgeResult.getExpertId()).get(0);
                judgeResultDTOList.add(new JudgeResultDTO(judgeResult, expert));
            }
            LineDataDTO lineDataDTO = new LineDataDTO();
            try {
                lineDataDTO = getLineDataDTO(FileUtil.linePath+"\\"+ctgData.getPakageNumber()+"\\"+ctgData.getCTG_number()+"1.txt");
            } catch (IOException e) {
                e.printStackTrace();
            }
            rt.add(new NewCTGDataDTO(ctgData, judgeResultDTOList, lineDataDTO));
        }
        return rt;
    }

    /**
     * @param pageEntity 分页信息（包含expertId）
     * @return
     */
    public List<NewCTGDataDTO> ctgDataByExpertPageable(PageEntity pageEntity){
        pageEntity.setPageStart( (pageEntity.getPage()-1)*pageEntity.getRows() );

        List<CTGData> ctgDataList = ctgDataDao.getByExpert(pageEntity);
        List<NewCTGDataDTO> rt = new ArrayList<>(ctgDataList.size());
        for (CTGData ctgData:
                ctgDataList) {
            List<JudgeResult> judgeResultList = judgeResultRepository.findAllByCtgCode(ctgData.getCtgCode());
            List<JudgeResultDTO> judgeResultDTOList = new ArrayList<JudgeResultDTO>(judgeResultList.size());
            for (JudgeResult judgeResult:
                    judgeResultList) {
                Expert expert = expertRepository.findByExpertId(judgeResult.getExpertId()).get(0);
                judgeResultDTOList.add(new JudgeResultDTO(judgeResult, expert));
            }
            LineDataDTO lineDataDTO = new LineDataDTO();
            try {
                lineDataDTO = getLineDataDTO(FileUtil.linePath+"\\"+ctgData.getPakageNumber()+"\\"+ctgData.getCTG_number()+"1.txt");
            } catch (IOException e) {
                e.printStackTrace();
            }
            rt.add(new NewCTGDataDTO(ctgData, judgeResultDTOList, lineDataDTO));
        }
        return rt;
    }

    /**
     * TODO 按页返回NST不同的数据
     * @param pageEntity
     * @return
     */
    public List<NewCTGDataDTO> ctgDataDifferentPageable(PageEntity pageEntity){
        pageEntity.setPageStart( (pageEntity.getPage()-1)*pageEntity.getRows() );

        List<CTGData> ctgDataList = ctgDataDao.getByDifferent(pageEntity);
        List<NewCTGDataDTO> rt = new ArrayList<>(ctgDataList.size());
        for (CTGData ctgData:
                ctgDataList) {
            List<JudgeResult> judgeResultList = judgeResultRepository.findAllByCtgCode(ctgData.getCtgCode());
            List<JudgeResultDTO> judgeResultDTOList = new ArrayList<JudgeResultDTO>(judgeResultList.size());
            for (JudgeResult judgeResult:
                    judgeResultList) {
                Expert expert = expertRepository.findByExpertId(judgeResult.getExpertId()).get(0);
                judgeResultDTOList.add(new JudgeResultDTO(judgeResult, expert));
            }
            LineDataDTO lineDataDTO = new LineDataDTO();
            try {
                lineDataDTO = getLineDataDTO(FileUtil.linePath+"\\"+ctgData.getPakageNumber()+"\\"+ctgData.getCTG_number()+"1.txt");
            } catch (IOException e) {
                e.printStackTrace();
            }
            rt.add(new NewCTGDataDTO(ctgData, judgeResultDTOList, lineDataDTO));
        }
        return rt;
    }

    public LineDataDTO getLineDataDTO(String filePath) throws IOException {
        File lineDataFile = new File(filePath);
        FileInputStream fis = new FileInputStream(lineDataFile);
        InputStreamReader inputStreamReader = new InputStreamReader(fis);
        BufferedReader bf = new BufferedReader(inputStreamReader);
        String str;
        LineDataDTO rt = new LineDataDTO();

        while ((str = bf.readLine()) != null) {
            System.out.println(str);
            Integer[] dataValue = {0,0,0};
            int index = 0;
            for (int i = 0; i < str.length(); i++){
                char c = str.charAt(i);
                if(Character.isDigit(c)){
                    //System.out.println(c);
                    dataValue[index] = dataValue[index]*10 + Integer.valueOf(c);
                }else{
                    index++;
                }
            }

            rt.fhr.add(dataValue[0]);
            rt.uc.add(dataValue[1]);
            rt.fm.add(dataValue[2]);
        }
        bf.close();
        inputStreamReader.close();
        fis.close();
        return rt;
    }

    public List<JudgeResult> judgeDataPageable(PageEntity pageEntity){
        return judgeResultDao.getByPage(pageEntity);
    }

    /**
     * 老接口，弃用，太慢太多——200W行
     * @param pageEntity
     * @return
     * @throws ParseException
     * @throws IOException
     */
    @Deprecated
    public List<LineDataDTO> lineDataByPackage(PageEntity pageEntity) throws ParseException, IOException {
        String packageName = pageEntity.getName();
        String[] dirProperties = packageName.split("-");
        int packageNumber = Integer.parseInt(dirProperties[0]);
        Date judgeDate = TimestampConstant.LINEDATE_FMT.parse(dirProperties[1]);
        String doctorId = dirProperties[2];
        String doctorGrade = dirProperties[3];
        // System.out.println(packageName+judgeDate+doctorId+doctorGrade);

        String dirString = FileUtil.linePath + "\\" + packageName;
        File packageDir = new File(dirString);
        String[] names = packageDir.list(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.endsWith(".txt");
            }
        });

        List<LineDataDTO> rt = new ArrayList<LineDataDTO>(names.length);
        for (String per:
                names) {
            System.out.println(per);
            File tempFile = new File(dirString + "\\" + per);
            FileInputStream fis = new FileInputStream(tempFile);
            InputStreamReader inputStreamReader = new InputStreamReader(fis);
            BufferedReader bf = new BufferedReader(inputStreamReader);
            String str;
            LineDataDTO tempData = new LineDataDTO();
//            tempData.setDoctorGrade(doctorGrade);
//            tempData.setDoctorId(doctorId);
//            tempData.setJudgeDate(judgeDate);
//            tempData.setPackageNumber(packageNumber);
//            tempData.setNumber(Integer.parseInt(per.substring(0,per.lastIndexOf('.'))));
            while ((str = bf.readLine()) != null) {
                System.out.println(str);
                Integer[] dataValue = {0,0,0};
                int index = 0;
                for (int i = 0; i < str.length(); i++){
                    char c = str.charAt(i);
                    if(Character.isDigit(c)){
                        System.out.println(c);
                        dataValue[index] = dataValue[index]*10 + Integer.valueOf(c);
                    }else{
                        index++;
                    }
                }

                tempData.fhr.add(dataValue[0]);
                tempData.uc.add(dataValue[1]);
                tempData.fm.add(dataValue[2]);
            }
            bf.close();
            inputStreamReader.close();
            fis.close();
            rt.add(tempData);
        }
        return rt;

    }
}
