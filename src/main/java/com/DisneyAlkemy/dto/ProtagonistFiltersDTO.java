package com.DisneyAlkemy.dto;

import lombok.Getter;
import lombok.Setter;


import java.util.Set;
/**
 *
 * @author Guille
 */
@Getter
@Setter
public class ProtagonistFiltersDTO {

    private String name;
    private String image;
    private Integer age;
    private Set<Long> Idmovie;

    public ProtagonistFiltersDTO(String name, String image, Integer age, Set<Long> idmovie) {
        this.name = name;
        this.image= image;
        this.age = age;
        Idmovie = idmovie;
    }
}
