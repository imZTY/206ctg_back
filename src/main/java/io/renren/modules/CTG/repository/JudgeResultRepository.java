package io.renren.modules.CTG.repository;

import io.renren.modules.CTG.entity.JudgeResult;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

/**
 * @author tianyi
 * @date 2018-12-16 20:34
 */
public interface JudgeResultRepository extends JpaRepository<JudgeResult,Integer> {
    public List<JudgeResult> findAllByCtgCode(String ctgCode);
}
