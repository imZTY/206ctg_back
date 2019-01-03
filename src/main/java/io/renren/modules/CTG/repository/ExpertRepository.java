package io.renren.modules.CTG.repository;

import io.renren.modules.CTG.entity.Expert;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

/**
 * @author tianyi
 * @date 2018-12-16 21:40
 */
public interface ExpertRepository extends JpaRepository<Expert,Integer> {
    public List<Expert> findByExpertID(String expertID);
}
