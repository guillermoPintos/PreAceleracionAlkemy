package com.DisneyAlkemy.repository;


import com.DisneyAlkemy.entity.MovieEntity;
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
public interface MovieRepository extends JpaRepository<MovieEntity, Long>, JpaSpecificationExecutor<MovieEntity> {

        List<MovieEntity> findAll(Specification<MovieEntity> spec);
}
