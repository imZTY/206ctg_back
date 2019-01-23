package io.renren.modules.CTG.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
 * 医师（专家）表
 * @author tianyi
 * @date 2018-11-26 10:44
 */
@Entity
@Data
public class Expert implements Serializable {
    @Id
    @GeneratedValue
    private int id;

    /**
     * 记录导入数据时的专家id信息
     */
    @Column(name = "expert_ID")
    @Pattern(regexp = "\\d+")
    private String expertID;

    /**
     * Grade
     级别
     （初级=1；2级=2；3级=3）
     */
    @NotNull
    private int grade;

    /**
     * 姓名
     */
    @NotBlank
    private String name = "暂无";

    /**
     * 所属机构
     */
    private String institution;

    /**
     * 职位
     */
    private String position;

    /**
     * 联系邮箱
     */
    @Email
    private String email;

    /**
     * 手机号码
     */
    private String phonenumber;
}
