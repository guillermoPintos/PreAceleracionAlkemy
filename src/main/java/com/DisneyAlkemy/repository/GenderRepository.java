package com.DisneyAlkemy.repository;


import com.DisneyAlkemy.entity.GenderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
/**
 *
 * @author Guille
 */
@Repository
public interface GenderRepository extends JpaRepository<GenderEntity, Long> {
}
