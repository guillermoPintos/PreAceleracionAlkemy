package com.DisneyAlkemy.dto;



import java.util.List;
import lombok.Getter;
import lombok.Setter;
/**
 *
 * @author Guille
 */
@Getter
@Setter
public class GenderDTO {


    private Long id;

    private String name;

    private String image;

    private List<MovieDTO> movieGenders;



}
