package com.DisneyAlkemy.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
/**
 *
 * @author Guille
 */
@Getter
@Setter
public class ProtagonistDTO {
    private Long id;
    private String image;
    private String name;
    private Integer age;
    private double weight;
    private String history;
    private List<MovieDTO> characterMovies;



}
