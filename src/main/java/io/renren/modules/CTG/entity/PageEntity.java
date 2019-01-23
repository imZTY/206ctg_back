package io.renren.modules.CTG.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author tianyi
 * @date 2019-01-15 22:13
 */
@Data
public class PageEntity implements Serializable {

    private int[] ids;

    private String name;
    /**
     * 固定设置为：每页30行
     */
    private int rows = 30;

    private int page;

    private int pageStart;
}
