package com.DisneyAlkemy.entity;



import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
/**
 *
 * @author Guille
 */

@Entity
@Table(name = "gender")
@Getter
@Setter
public class GenderEntity {

    @Id
    @GeneratedValue( strategy = GenerationType.SEQUENCE)
    private Long id;

    private String name;

    private String image;





}

