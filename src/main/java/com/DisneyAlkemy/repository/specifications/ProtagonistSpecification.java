package com.DisneyAlkemy.repository.specifications;


import com.DisneyAlkemy.dto.ProtagonistFiltersDTO;
import com.DisneyAlkemy.entity.MovieEntity;
import com.DisneyAlkemy.entity.ProtagonistEntity;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@Component
public class ProtagonistSpecification {

    public Specification<ProtagonistEntity> getByFilters(ProtagonistFiltersDTO filtersDTO) {
        return (root, query, specificationBuilder) -> {

            List<Predicate> predicates = new ArrayList<>();
            //name filter
            if (StringUtils.hasLength(filtersDTO.getName())) {
                predicates.add(
                        specificationBuilder.like(
                                specificationBuilder.lower(root.get("name")),
                                "%" + filtersDTO.getName().toLowerCase() + "%"
                        )

                );
            }
            //age filter
            if (filtersDTO.getAge() != null) {
                predicates.add(
                        specificationBuilder.equal(root.get("age"), filtersDTO.getAge()));

            }
            //movies filter
            if (!CollectionUtils.isEmpty(filtersDTO.getIdmovie())) {
                Join<ProtagonistEntity, MovieEntity> join = root.join("movies", JoinType.INNER);
                Expression<String> moviesIDS = join.get("id");
                predicates.add(moviesIDS.in(filtersDTO.getIdmovie()));

            }

            //image filter
            if (StringUtils.hasLength(filtersDTO.getImage())) {
                predicates.add(
                        specificationBuilder.like(
                                specificationBuilder.lower(root.get("image")),
                                "%" + filtersDTO.getImage().toLowerCase() + "%"
                        )

                );
            }




            return specificationBuilder.and(predicates.toArray(new javax.persistence.criteria.Predicate[0]));
        };
    }
}
