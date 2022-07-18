package com.DisneyAlkemy.service.impl;


import com.DisneyAlkemy.dto.MovieBasicDTO;
import com.DisneyAlkemy.dto.MovieDTO;
import com.DisneyAlkemy.dto.MovieFiltersDTO;
import com.DisneyAlkemy.entity.MovieEntity;
import com.DisneyAlkemy.exception.ParamNotFound;
import com.DisneyAlkemy.mapper.MovieMapper;
import com.DisneyAlkemy.repository.GenderRepository;
import com.DisneyAlkemy.repository.MovieRepository;
import com.DisneyAlkemy.repository.specifications.MovieSpecification;
import com.DisneyAlkemy.service.MovieService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;



import java.util.Optional;
import java.util.Set;


@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    private MovieMapper movieMapper;
    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private GenderRepository genderRepository;
    @Autowired
    private MovieSpecification movieSpecification;


    public MovieServiceImpl( @Lazy MovieMapper movieMapper,  @Lazy MovieRepository movieRepository,  @Lazy GenderRepository genderRepository,  @Lazy MovieSpecification movieSpecification) {
        this.movieMapper = movieMapper;
        this.movieRepository = movieRepository;
        this.genderRepository = genderRepository;
        this.movieSpecification = movieSpecification;
    }

    //Post
    @Override
    public MovieDTO save(MovieDTO movie) {

        MovieEntity movieEntity = movieMapper.movieDTO2Entity(movie);
        MovieEntity savedMovie = movieRepository.save(movieEntity);
        MovieDTO result = movieMapper.movieEntity2DTO(savedMovie, false);

        return result;
    }

    //delete
    @Override
    public void delete(Long id) {
        movieRepository.deleteById(id);
    }
    //Put
    @Override
    public MovieDTO putMovie(Long id, MovieDTO edit) {

        MovieEntity savedMovie = this.getmovieById(id);

        savedMovie.setImage(edit.getImage());
        savedMovie.setTitle(edit.getTitle());
        savedMovie.setDateCreation(movieMapper.String2LocalDate(edit.getDateCreation()));
        savedMovie.setQualification(edit.getQualification());

        MovieEntity editMovie = movieRepository.save(savedMovie);
        MovieDTO  saveDTO = movieMapper.movieEntity2DTO(editMovie, false);

        return saveDTO;
    }


    private MovieEntity getmovieById(Long id) {
        Optional<MovieEntity> movieEntity = movieRepository.findById(id);
        if (!movieEntity.isPresent()){
            throw new ParamNotFound("Id no valido");
        }
        return movieEntity.get();
    }

    //filters

    public List<MovieDTO> getByFilters(String title, String image, String dateCreation, Set<Long> gender, String order){

        MovieFiltersDTO filtersDTO = new MovieFiltersDTO(title, image, dateCreation, gender, order);

        List<MovieEntity> entities = movieRepository.findAll(movieSpecification.getByFilters(filtersDTO));
        List<MovieDTO> resultado = movieMapper.movieEntityList2DtoList(entities, true);

        return resultado;
    }
    
    //basic

   @Override
    public List<MovieBasicDTO> getAllBasics() {

        List<MovieEntity> movieEntities= movieRepository.findAll();
        List<MovieBasicDTO> resultado =  movieMapper.movieBasicEntityList2DtoList(movieEntities);

        return resultado;

    }

 


}
