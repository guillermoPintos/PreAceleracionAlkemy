package com.DisneyAlkemy.mapper;


import com.DisneyAlkemy.dto.GenderDTO;
import com.DisneyAlkemy.entity.GenderEntity;
import org.springframework.stereotype.Component;

@Component
public class GenderMapper {

    // DTO to Entity
    public GenderEntity genderDTO2Entity(GenderDTO gender) {

        GenderEntity genderEntity = new GenderEntity();
        genderEntity.setImage(gender.getImage());
        genderEntity.setName(gender.getName());

        return genderEntity;
    }

   // Entity to DTO
    public GenderDTO genderEntity2DTO(GenderEntity entity) {

        GenderDTO genderDTO = new GenderDTO();

        genderDTO.setImage(entity.getImage());
        genderDTO.setName(entity.getName());
        genderDTO.setId(entity.getId());

        return genderDTO;
    }
}
