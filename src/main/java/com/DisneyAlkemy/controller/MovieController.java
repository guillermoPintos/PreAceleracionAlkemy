
package com.DisneyAlkemy.controller;

import com.DisneyAlkemy.dto.MovieBasicDTO;
import com.DisneyAlkemy.dto.MovieDTO;
import com.DisneyAlkemy.service.MovieService;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;



/**
 *
 * @author Guille
 */
@RestController
@RequestMapping("movies")
public class MovieController {


    private MovieService movieService;

    public MovieController(@Autowired @Lazy MovieService movieService) {
        this.movieService = movieService;
    }

    @PostMapping
    public ResponseEntity<MovieDTO> save(@RequestBody MovieDTO movie){

        MovieDTO savedMovie= movieService.save(movie);

    return ResponseEntity.status(HttpStatus.CREATED).body(savedMovie);
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void>delete(@PathVariable Long id){
        this.movieService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


    @PutMapping("/{id}")
    public ResponseEntity<MovieDTO>put(@PathVariable Long id, @RequestBody MovieDTO edit){
        MovieDTO putMovie = movieService.putMovie(id,edit);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(putMovie);
    }

    //Get para traer una LISTA de imagen, titulo y fecha de creacion
    @GetMapping("/all")
    public  ResponseEntity<List<MovieBasicDTO>> getAllBasic(){
        List<MovieBasicDTO> basicMovies = movieService.getAllBasics();
        return ResponseEntity.status(HttpStatus.OK).body(basicMovies);
    }

    // Get para todos los atributos y con Filtros

    @GetMapping
    public ResponseEntity<List<MovieDTO>> getDetailsByFilters(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String image,
            @RequestParam(required = false) String dateCreation,
            @RequestParam(required = false) Set<Long> gender,
            @RequestParam(required = false, defaultValue = "ASC") String order)
    {
        List<MovieDTO> moviesListDto = this.movieService.getByFilters(title, image, dateCreation,gender,order);

        return ResponseEntity.status(HttpStatus.OK).body(moviesListDto);

    }




}

