package io.renren.modules.CTG.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.Date;

/**
 * 孕妇表
 * @author tianyi
 * @date 2018-11-26 10:44
 */
@Entity
@Data
public class Gravida implements Serializable {
    @Id
    @GeneratedValue
    private int id;

    /**
     * 记录导入数据时的数据包特征信息
     */
    @Column(name = "pregnant_ID")
    @Pattern(regexp = "\\d+")
    private String pregnantID;

    private Date birthday;

    private boolean isHighRisk;

    /**
     * Gestational Age
     (孕周）
     */
    @Column(name = "Gestational_Age")
    private double Gestational_Age;
}
