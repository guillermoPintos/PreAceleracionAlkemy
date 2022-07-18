package com.DisneyAlkemy.entity;


import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Guille
 */
@Entity
@Table (name = "movie")
@Getter
@Setter

public class MovieEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String image;

    private String title;

    @Column(name = "creation_date")
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private LocalDate dateCreation;

    private int qualification;


    /*characters relation*/

    @ManyToMany(
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            },
            fetch = FetchType.LAZY
    )@JoinTable(
            name = "movie_character",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "character_id")

    )
    private List<ProtagonistEntity> characters = new ArrayList<>();


    /*genders relation*/

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    private GenderEntity gender;






}