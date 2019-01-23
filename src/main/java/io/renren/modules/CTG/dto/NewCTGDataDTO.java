package io.renren.modules.CTG.dto;

import io.renren.modules.CTG.entity.CTGData;
import io.renren.modules.CTG.entity.JudgeResult;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * @author tianyi
 * @date 2019-01-21 11:31
 */
@Data
@AllArgsConstructor
public class NewCTGDataDTO {

    private CTGData ctgData;

    private List<JudgeResultDTO> judgeResultDTOList;

    private LineDataDTO lineDataDTO;
}
