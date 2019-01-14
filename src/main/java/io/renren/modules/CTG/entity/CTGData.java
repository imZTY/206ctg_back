package io.renren.modules.CTG.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.*;
import java.util.Date;

/**
 * CTG数据表
 * @author tianyi
 * @date 2018-11-26 10:40
 */
@Entity
@Data
public class CTGData {
    @Id
    @GeneratedValue
    private int id;

    /**
     * 记录导入数据时的数据包特征信息
     */
//    @Column(name = "ctg_data_ID")
//    @Pattern(regexp = "\\d+")
//    private String dataImportID;

    /**
     * Pakage_number
     数据包编号
     */
    @Pattern(regexp = "\\d+")
    @NotBlank
    private String pakageNumber;

    /**
     * CTG_number
     在数据包中的编号
     */
    @Column(name = "CTG_number")
    @Pattern(regexp = "\\d+")
    @NotBlank
    private String CTG_number;

    /**
     * pregnantID
     孕妇编号
     （外键）<-实际并不是键
     */
    @Column(name = "Gravida_ID")
    @NotNull
    private int Gravida_ID;

    /**
     * Gestational Age
     (孕周）
     */
    @Column(name = "Gestational_Age")
    @NotNull
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
    @NotNull
    private Date checkDate;

    /**
     * 判断日期
     * Time
     CTG检验时间
     */
    @NotNull
    private Date judgeDate;

    /**
     * 来源文件的文件名
     */
//    @Pattern(regexp = "\\*.txt$")
    @NotBlank
    private String fileName;

    /**
     * 设备商标（品牌）
     */
    @NotBlank
    private String deviceBrand = "暂无";

    /**
     * 设备型号（产品版本）
     */
    @NotBlank
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
    @NotNull
    private int expertReadBegin = 0;

    @NotNull
    private int expertReadEnd = 0;

    /* ================ 数据变量 begin ================= */

    /**
     * BASELINE
     基线值
     （bpm）
     */
    @NotNull
    @Min(0)
    private int baseLine = 0;

    /**
     * FHRVAR
     变异
     （bpm）
     */
    @NotNull
    @Min(0)
    private int FHRVAR = 0;

    /**
     * FHRCYCLE
     周期变异
     (bpm)
     */
    @NotNull
    @Min(0)
    private int FHRCYCLE = 0;

    /**
     * FHRFAST
     加速
     （次）
     */
    @NotNull
    @Min(0)
    private int FHRFAST = 0;

    /**
     * FHSLOW
     减速
     （次）
     */
    @NotNull
    @Min(0)
    private int FHSLOW = 0;

    /**
     * ED
     早减
     (次)
     */
    @NotNull
    @Min(0)
    private int ED = 0;

    /**
     * LD
     晚减
     (次)
     */
    @NotNull
    @Min(0)
    private int LD = 0;

    /**
     * VD
     变异减速
     (次）
     */
    @NotNull
    @Min(0)
    private int VD = 0;

    /**
     * DP
     延长减速
     （次）
     */
    @NotNull
    @Min(0)
    private int DP = 0;

    /**
     * DL
     轻减速
     （次）
     */
    @NotNull
    @Min(0)
    private int DL = 0;

    /**
     * DS
     严重的减速
     （次）
     */
    @NotNull
    @Min(0)
    private int DS = 0;

    /**
     * FMOVE
     胎动
     （次）
     */
    @NotNull
    @Min(0)
    private int FMOVE = 0;

    /**
     * UCTIMES
     子宫收缩
     （次）
     */
    @NotNull
    @Min(0)
    private int UCTIMES = 0;

    /**
     * FASTTIME
     加速时间
     （秒）
     */
    @NotNull
    @Min(0)
    private int FASTTIME = 0;

    /**
     * FAETVALUE
     加速幅度
     （bpm)
     */
    @NotNull
    @Min(0)
    private int FAETVALUE = 0;

    /**
     * STV
     短变异时间
     （毫秒）
     */
    @NotNull
    @Min(0)
    private Double STV = 0.0;

    /**
     * HIGHLTV
     高变异时间
     （分钟）
     */
    @NotNull
    @Min(0)
    private int HIGHLTV = 0;

    /**
     * LOWLTV
     低变异时间
     （分钟）
     */
    @NotNull
    @Min(0)
    private int LOWLTV = 0;

    /**
     * HAVEACCDEC
     * 有加速减速时间
     （秒）
     */
    @NotNull
    @Min(0)
    private int HAVEACCDEC = 0;

    /**
     * 数据丢失率
     （%）
     */
    @NotNull
    @Max(100)
    @Min(0)
    private int lostRate = 0;

    /**
     * UCSTRONG
     宫缩强度
     （%）
     */
    @NotNull
    @Max(100)
    @Min(0)
    private int UCSTRONG = 0;

    /**
     * UCNEXT
     宫缩间隔
     (秒）
     */
    @NotNull
    @Min(0)
    private int UCNEXT = 0;

    /**
     * UCKTIME
     宫缩持续时间
     (秒）
     */
    @NotNull
    @Min(0)
    private int UCKTIME = 0;

    /* ================ 数据变量 end =================== */

    /**
     * 判读结果
     * NST
     Fail=4;Normal=1; Suspect=2; Pathologic=3; notyet=0
     不满意=0；有反应= 1;可疑= 2;无反应= 3;未判读 = 4
     */
    @NotNull
    private int NST = 4;
}
