package io.renren.modules.CTG.dto;

import lombok.Data;

import java.util.Date;

/**
 * @author tianyi
 * @date 2018-12-17 13:53
 */
@Data
public class AllDataDTO {

    /**
     * Pakage_number
     数据包编号
     */
    private String pakageNumber;

    /**
     * CTG_number
     在数据包中的编号
     */
    private String CTG_number;

    /**
     * pregnantID
     孕妇编号
     （外键）<-实际并不是键
     */
    private int Gravida_ID;

    /**
     * Gestational Age
     (孕周）
     */
    private double Gestational_Age;

    /**
     * 检测地点
     （1=医院；2=家庭；3=社区医院）
     */
    private int checkPlace;

    /**
     * 次数
     */
    private int times;
    /**
     * 监护时长
     */
    private int watchTime;

    /**
     * 年龄
     */
    private int age;

    /**
     * 检查日期
     * Date
     CTG检验日期
     */
    private Date checkDate;

    /**
     * 判断日期
     * Time
     CTG检验时间
     */
    private Date judgeDate;

    /**
     * 来源文件的文件名
     */
//    @Pattern(regexp = "\\*.txt$")
    private String fileName;

    /**
     * 设备商标（品牌）
     */
    private String deviceBrand = "暂无";

    /**
     * 设备型号（产品版本）
     */
    private String deviceEdition = "暂无";

    /**
     * 设备的生产批次信息
     */
    private String deviceBatch;

    /**
     * TODO
     * expertReadBegin 专家判读开始时间戳
     * expertReadEnd 专家判读结束时间戳
     */
    private int expertReadBegin = 0;

    private int expertReadEnd = 0;

    /* ================ 数据变量 begin ================= */

    /**
     * LB
     基线值
     （bpm）
     */
    private int LB = 0;

    /**
     * LTV
     长变异
     （bpm）
     */
    private int LTV = 0;

    /**
     * PV
     周期变异
     (bpm)
     */
    private int PV = 0;

    /**
     * AC
     加速
     （次/秒）
     */
    private int AC = 0;

    /**
     * DC
     减速
     （次/秒）
     */
    private int DC = 0;

    /**
     * ED
     早减
     (次/秒)
     */
    private int ED = 0;

    /**
     * LD
     晚减
     (次/秒)
     */
    private int LD = 0;

    /**
     * VD
     变异减速
     (次/秒）
     */
    private int VD = 0;

    /**
     * DP
     延长减速
     （次/秒）
     */
    private int DP = 0;

    /**
     * DL
     轻减速
     （次/秒）
     */
    private int DL = 0;

    /**
     * DS
     严重的减速
     （次/秒）
     */
    private int DS = 0;

    /**
     * FM
     胎动
     （次/秒）
     */
    private int FM = 0;

    /**
     * UC
     子宫收缩
     （次/秒）
     */
    private int UC = 0;

    /**
     * AT
     加速时间
     （秒）
     */
    private int AI = 0;

    /**
     * AA
     加速幅度
     （bpm)
     */
    private int AA = 0;

    /**
     * STV
     短变异时间
     （秒）
     */
    private Double STV = 0.0;

    /**
     * HVT
     高变异时间
     （分钟）
     */
    private int HVT = 0;

    /**
     * LVT
     低变异时间
     （分钟）
     */
    private int LVT = 0;

    /**
     * 有加速减速时间
     （分钟）
     */
    private int speedChangingTime = 0;

    /**
     * 数据丢失率
     （%）
     */
    private int lostRate = 0;

    /**
     * 宫缩强度
     （%）
     */
//    private int UCStrengthPercent = 0;
//
//    private int UCIntervalSecond = 0;

    /* ================ 数据变量 end =================== */

    /**
     * 判读结果
     * NST
     Fail=4;Normal=1; Suspect=2; Pathologic=3; notyet=0
     不满意=0；有反应= 1;可疑= 2;无反应= 3;未判读 = 4
     */
    private int NST = 4;

    // TODO [ CTG_data ———— JudgeResult ] 分界线


}
