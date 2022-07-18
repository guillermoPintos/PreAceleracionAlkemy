
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
public class MovieDTO {
    


    private Long id;

    private String image;

    private String title;

    private String dateCreation;

    private int qualification;

    private Long genderId;

    private List<ProtagonistDTO> movieCharacters;



}
