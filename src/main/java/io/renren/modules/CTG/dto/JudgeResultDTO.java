package io.renren.modules.CTG.dto;

import io.renren.modules.CTG.entity.Expert;
import io.renren.modules.CTG.entity.JudgeResult;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * @author tianyi
 * @date 2019-01-21 12:40
 */
@Data
@AllArgsConstructor
public class JudgeResultDTO {

    private JudgeResult judgeResult;

    private Expert expert;
}
