package com.DisneyAlkemy.mapper;


import com.DisneyAlkemy.dto.ProtagonistBasicDTO;
import com.DisneyAlkemy.dto.ProtagonistDTO;
import com.DisneyAlkemy.entity.ProtagonistEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProtagonistMapper {

    @Autowired
    private MovieMapper movieMapper;

    public ProtagonistMapper( @Lazy MovieMapper movieMapper) {

        this.movieMapper = movieMapper;
    }

    //DTO a Entidad

    public ProtagonistEntity protagonistDTO2Entity(ProtagonistDTO dto){

        ProtagonistEntity characterEntity = new ProtagonistEntity();

        characterEntity.setAge(dto.getAge());
        characterEntity.setHistory(dto.getHistory());
        characterEntity.setImage(dto.getImage());
        characterEntity.setName(dto.getName());
        characterEntity.setWeight(dto.getWeight());

        return characterEntity;
    }

    //Entidad a DTO

    public ProtagonistDTO protagonistEntity2DTO(ProtagonistEntity entity, boolean b) {

        ProtagonistDTO dto = new ProtagonistDTO();

        dto.setId(entity.getId());
        dto.setAge(entity.getAge());
        dto.setHistory(entity.getHistory());
        dto.setImage(entity.getImage());
        dto.setName(entity.getName());
        dto.setWeight(entity.getWeight());

        if (b){
            dto.setCharacterMovies(movieMapper.movieEntityList2DtoList(entity.getMovies(), false));
        }

        return dto;
    }


    public List<ProtagonistDTO>protagonistEntityList2DTOList(List<ProtagonistEntity> listEntity, boolean b) {
        List<ProtagonistDTO> dtoList = new ArrayList<>();
        for (ProtagonistEntity entity : listEntity) {
            
            dtoList.add(this.protagonistEntity2DTO(entity, b));

        }
        return dtoList;
    }

    public List<ProtagonistEntity> protagonistEntityList(List<ProtagonistDTO> movieCharacters) {

        List<ProtagonistEntity>protagonistEntityLista = new ArrayList<>();

        for(ProtagonistDTO aux:  movieCharacters){
            protagonistEntityLista.add(this.protagonistDTO2Entity(aux));
        }

        return protagonistEntityLista;



    }

    //trabajamos con la lista de protagonists basic

    public List<ProtagonistBasicDTO> protagonistEntityList2DTOListBasic(List<ProtagonistEntity> entityList) {

        List<ProtagonistBasicDTO>dtoLista = new ArrayList<>();
        for(ProtagonistEntity aux : entityList){
            dtoLista.add(this.protagonistEntity2DtoBasic(aux));
        }
        return dtoLista;

    }

   private ProtagonistBasicDTO protagonistEntity2DtoBasic(ProtagonistEntity aux) {

        ProtagonistBasicDTO protagonistBasicDTO = new ProtagonistBasicDTO();

        protagonistBasicDTO.setImage(aux.getImage());
        protagonistBasicDTO.setName(aux.getName());

        return  protagonistBasicDTO;
    }



}
