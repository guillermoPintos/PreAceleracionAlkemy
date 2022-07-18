package com.DisneyAlkemy.controller;


import com.DisneyAlkemy.dto.GenderDTO;
import com.DisneyAlkemy.service.GenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
/**
 *
 * @author Guille
 */
@RestController
@RequestMapping("genders")
public class GenderController {

    @Autowired
    private GenderService genderService;

    public GenderController( @Lazy GenderService genderService) {

        this.genderService = genderService;
    }

    @PostMapping
    public ResponseEntity<GenderDTO> save(@RequestBody GenderDTO gender){

        GenderDTO savedGender = genderService.save(gender);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedGender);
    }





}
