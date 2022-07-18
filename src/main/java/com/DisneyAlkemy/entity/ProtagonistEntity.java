package com.DisneyAlkemy.entity;


import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Guille
 */
@Entity
@Table (name = "characters")
@Getter
@Setter
@SQLDelete( sql = "UPDATE  characters SET deleted = true WHERE id=?")
@Where( clause = "deleted=false")
public class ProtagonistEntity {

    @Id
    @GeneratedValue (strategy = GenerationType.SEQUENCE)
    private Long id;

    private String image;

    private String name;

    private Integer age;

    private double weight;

    private String history;

    /*relation whit movies*/
    @ManyToMany(mappedBy = "characters", fetch  = FetchType.LAZY)
    private List<MovieEntity> movies = new ArrayList<>();


}
