package io.renren.modules.CTG.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Date;

/**
 * CTG数据表
 * @author tianyi
 * @date 2018-11-26 10:40
 */
@Entity
@Data
public class CTGData implements Serializable {
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
    @Column(name = "gravidaId")
    @NotNull
    private int gravidaId;

    /**
     * Gestational Age
     (孕周）
     */
    @Column(name = "gestationalAge")
    @NotNull
    private double gestationalAge;

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
     * brand
     测量仪器品牌
     */
    @NotBlank
    private String deviceBrand = "暂无";

    /**
     * model
     测量仪器型号
     */
    @NotBlank
    private String deviceEdition = "暂无";

    /**
     * batch
     测量仪器批次（选填）
     */
    private String deviceBatch;

    /**
     * 这里不记录为时间戳是因为数据来源无法转为时间戳
     * expertReadBegin 专家判读开始时间 BD列
     * expertReadEnd 专家判读结束时间 BE列
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
     * FASTVALUE
     加速幅度
     （bpm)
     */
    @NotNull
    @Min(0)
    private int FASTVALUE = 0;

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

    /**
     * VDTIME
     变异减速持续时间
     （秒）
     */
    @NotNull
    @Min(0)
    private int VDTIME = 0;

    /**
     * FRH_EVALUATION
     胎心率评价
     */
    private int FRH_EVALUATION = 0;

    /**
     * VAR_EVALUATION
     长变异评价
     */
    private int VAR_EVALUATION = 0;

    /**
     * FHSLOW_EVALUATION
     减速评价
     */
    private int FHSLOW_EVALUATION = 0;

    /**
     * FHRFAST_EVALUATION_1
     足月加速评价
     */
    private int FHRFAST_EVALUATION_1 = 0;

    /**
     * FHRFAST_EVALUATION_0
     不足月加速评价
     */
    private int FHRFAST_EVALUATION_0 = 0;

    /**
     * NST_EVALUATION
     NST处理评价
     */
    private int NST_EVALUATION = 0;

    /* ================ 数据变量 end =================== */

    /**
     * 判读结果
     * NST
     Fail=4; Normal=1; Suspect=2; Pathologic=3; notyet=0
     不满意=0；有反应= 1;可疑= 2;无反应= 3;未判读 = 4
     */
    @NotNull
    private int NST = 4;

    @NotNull
    private long upTime = 0L;

    @NotBlank
    private String ctgCode;
}
