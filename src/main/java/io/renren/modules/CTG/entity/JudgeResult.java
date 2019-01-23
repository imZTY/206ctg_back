package io.renren.modules.CTG.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * 判读结果表
 * @author tianyi
 * @date 2018-11-26 10:40
 */
@Entity
@Data
public class JudgeResult implements Serializable {
    @Id
    @GeneratedValue
    private int id;

    @Column(name = "ctgId")
    @NotNull
    private int ctgId;

    @Column(name = "expertId")
    @NotBlank
    private String expertId;

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
     (次/秒)
     */
    @NotNull
    @Min(0)
    private int ED = 0;

    /**
     * LD
     晚减
     (次/秒)
     */
    @NotNull
    @Min(0)
    private int LD = 0;

    /**
     * VD
     变异减速
     (次/秒）
     */
    @NotNull
    @Min(0)
    private int VD = 0;

    /**
     * DP
     延长减速
     （次/秒）
     */
    @NotNull
    @Min(0)
    private int DP = 0;

    /**
     * DL
     轻减速
     （次/秒）
     */
    @NotNull
    @Min(0)
    private int DL = 0;

    /**
     * DS
     严重的减速
     （次/秒）
     */
    @NotNull
    @Min(0)
    private int DS = 0;

    /**
     * FMOVE
     胎动
     （次/秒）
     */
    @NotNull
    @Min(0)
    private int FMOVE = 0;

    /**
     * UCTIMES
     子宫收缩
     （次/秒）
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

    /* ================ 数据变量 end =================== */

    /**
     * 判断日期
     * Time
     CTG检验时间
     */
    @NotNull
    private Date judgeDate;

    /**
     * 判读结果
     * NST
     Fail=4;Normal=1; Suspect=2; Pathologic=3; notyet=0
     不满意=0；有反应= 1;可疑= 2;无反应= 3;未判读 = 4
     */
    @NotNull
    private int NST = 4;

    /**
     * 备注
     */
    private String notes;

    @NotNull
    private long upTime = 0L;

    @NotBlank
    private String ctgCode;
}
