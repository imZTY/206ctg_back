package io.renren.modules.sys.dao.ctg;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import io.renren.modules.CTG.entity.CTGData;
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
public interface ICTGDataDao extends BaseMapper<CTGData> {

    List getByPage(PageEntity pageEntity);

    int deleteRepeat();
}
