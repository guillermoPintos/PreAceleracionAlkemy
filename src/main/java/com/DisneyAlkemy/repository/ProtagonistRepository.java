package com.DisneyAlkemy.repository;


import com.DisneyAlkemy.entity.ProtagonistEntity;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
/**
 *
 * @author Guille
 */
@Repository
public interface ProtagonistRepository extends JpaRepository<ProtagonistEntity, Long>, JpaSpecificationExecutor<ProtagonistEntity> {


   List<ProtagonistEntity> findAll(Specification<ProtagonistEntity> spec);

}
