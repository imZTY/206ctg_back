package io.renren.modules.CTG.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author tianyi
 * @date 2019-01-19 15:56
 */
@Data
public class LineDataDTO {

    public LineDataDTO(){
        this.fhr = new ArrayList<Integer>();
        this.uc = new ArrayList<Integer>();
        this.fm = new ArrayList<Integer>();
    }

//    // 对应ctg数据的编号
//    private int number;
//
//    // 数据包号
//    private int packageNumber;
//
//    private String doctorId;
//
//    private String doctorGrade;
//
//    private Date judgeDate;

    public List<Integer> fhr;

    public List<Integer> uc;

    public List<Integer> fm;
}
