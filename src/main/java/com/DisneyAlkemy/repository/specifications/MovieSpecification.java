
package com.DisneyAlkemy.repository.specifications;


import com.DisneyAlkemy.dto.MovieFiltersDTO;
import com.DisneyAlkemy.entity.GenderEntity;
import com.DisneyAlkemy.entity.MovieEntity;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Guille
 */
@Component
public class MovieSpecification {

    public Specification<MovieEntity> getByFilters(MovieFiltersDTO filtersDTO){
        return (root, query, specificationBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            //title filter
            if (StringUtils.hasLength(filtersDTO.getTitle())) {
                predicates.add(
                        specificationBuilder.like(
                                specificationBuilder.lower(root.get("title")),
                                "%" + filtersDTO.getTitle().toLowerCase() + "%"
                        )
                );
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

            //date filter
            if (StringUtils.hasLength(filtersDTO.getDateCreation())){
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate date = LocalDate.parse(filtersDTO.getDateCreation(), formatter);

                predicates.add(
                        specificationBuilder.equal(root.get("dateCreation"), date)
                );
            }

            //gender filter
            if (!CollectionUtils.isEmpty(filtersDTO.getGender())) {
                Join<MovieEntity, GenderEntity> join = root.join("gender", JoinType.INNER);
                Expression<String> genderId = join.get("id");
                predicates.add(genderId.in(filtersDTO.getGender()));
            }

            query.distinct(true);

            //order filter
            String orderByField = "title";
            query.orderBy(
                    filtersDTO.isASC() ?
                            specificationBuilder.asc(root.get(orderByField)) :
                            specificationBuilder.desc(root.get(orderByField))
            );
            return specificationBuilder.and(predicates.toArray(new Predicate[0]));

        };

    }
}
