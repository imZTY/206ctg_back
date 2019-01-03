package io.renren.modules.CTG.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 判读结果表
 * @author tianyi
 * @date 2018-11-26 10:40
 */
@Entity
@Data
public class JudgeResult {
    @Id
    @GeneratedValue
    private int id;

    @Column(name = "CTG_ID")
    @NotNull
    private int CTG_ID;

    @Column(name = "Expert_ID")
    @NotBlank
    private String Expert_ID;

    /* ================ 数据变量 begin ================= */

    /**
     * LB
     基线值
     （bpm）
     */
    @NotNull
    @Min(0)
    private int LB = 0;

    /**
     * LTV
     长变异
     （bpm）
     */
    @NotNull
    @Min(0)
    private int LTV = 0;

    /**
     * PV
     周期变异
     (bpm)
     */
    @NotNull
    @Min(0)
    private int PV = 0;

    /**
     * AC
     加速
     （次/秒）
     */
    @NotNull
    @Min(0)
    private int AC = 0;

    /**
     * DC
     减速
     （次/秒）
     */
    @NotNull
    @Min(0)
    private int DC = 0;

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
     * FM
     胎动
     （次/秒）
     */
    @NotNull
    @Min(0)
    private int FM = 0;

    /**
     * UC
     子宫收缩
     （次/秒）
     */
    @NotNull
    @Min(0)
    private int UC = 0;

    /**
     * AT
     加速时间
     （秒）
     */
    @NotNull
    @Min(0)
    private int AI = 0;

    /**
     * AA
     加速幅度
     （bpm)
     */
    @NotNull
    @Min(0)
    private int AA = 0;

    /* ================ 数据变量 end =================== */

    /**
     * TODO 判读耗时？
     */
    @NotNull
    private int costTime = 0;

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
}
