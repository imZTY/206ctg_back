package io.renren.modules.sys.dao.ctg;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import io.renren.modules.CTG.entity.JudgeResult;
import io.renren.modules.CTG.entity.PageEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author tianyi
 * @date 2019-01-18 23:23
 */
@Mapper
@Component
public interface IJudgeResultDao extends BaseMapper<JudgeResult> {

    List getByPage(PageEntity pageEntity);

    int deleteRepeat();
}
