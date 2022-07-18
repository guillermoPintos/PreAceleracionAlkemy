package com.DisneyAlkemy.mapper;


import com.DisneyAlkemy.dto.MovieBasicDTO;
import com.DisneyAlkemy.dto.MovieDTO;
import com.DisneyAlkemy.entity.GenderEntity;
import com.DisneyAlkemy.entity.MovieEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Guille
 */
@Component
public class MovieMapper {
    @Autowired
    private ProtagonistMapper protagonistMapper;

    public MovieMapper( @Lazy ProtagonistMapper protagonistMapper) {

        this.protagonistMapper = protagonistMapper;
    }


    // DTO to Entity

    public MovieEntity movieDTO2Entity(MovieDTO dto) {

        GenderEntity genderEntity = new GenderEntity();
        MovieEntity movieEntity = new MovieEntity();

        movieEntity.setTitle(dto.getTitle());
        movieEntity.setDateCreation(this.String2LocalDate(dto.getDateCreation()));
        movieEntity.setImage(dto.getImage());
        movieEntity.setQualification(dto.getQualification());
        movieEntity.setId(dto.getGenderId());
        movieEntity.setCharacters(protagonistMapper.protagonistEntityList(dto.getMovieCharacters()));
        movieEntity.setGender(genderEntity);

        return movieEntity;
    }

    //Entity to DTO

    public MovieDTO movieEntity2DTO(MovieEntity entity, boolean showCharacters){

        MovieDTO dto = new MovieDTO();

        dto.setId(entity.getId());
        dto.setTitle(entity.getTitle());
        dto.setDateCreation(entity.getDateCreation().toString());
        dto.setImage(entity.getImage());
        dto.setQualification(entity.getQualification());
        dto.setGenderId(entity.getGender().getId());
        dto.setMovieCharacters(protagonistMapper.protagonistEntityList2DTOList(entity.getCharacters(), false));


        return dto;
    }

    //ListEntity to ListDto

    public List<MovieDTO> movieEntityList2DtoList(List<MovieEntity> listEntity, boolean b){
        List<MovieDTO>dtoList = new ArrayList<>();
        for(MovieEntity ent : listEntity){
            dtoList.add(this.movieEntity2DTO(ent, b));
        }
        return dtoList;
    }


    //Date

    public LocalDate String2LocalDate(String stringDate) {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(stringDate, format);
        return date;
    }

    //basics atributes

    public List<MovieBasicDTO> movieBasicEntityList2DtoList(List<MovieEntity> movieEntities) {
        List<MovieBasicDTO>dtoList = new ArrayList<>();
        for(MovieEntity aux : movieEntities){
            dtoList.add(this.movieBasicEntity2Dto(aux));
        }
        return dtoList;
    }
    private MovieBasicDTO movieBasicEntity2Dto(MovieEntity aux) {

        MovieBasicDTO movieBasicDTO = new MovieBasicDTO();

        movieBasicDTO.setImage(aux.getImage());
        movieBasicDTO.setTitle(aux.getTitle());
        movieBasicDTO.setDateCreation(aux.getDateCreation().toString());

        return movieBasicDTO;
    }
}


