package com.DisneyAlkemy.service.impl;


import com.DisneyAlkemy.dto.GenderDTO;
import com.DisneyAlkemy.entity.GenderEntity;
import com.DisneyAlkemy.mapper.GenderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import com.DisneyAlkemy.repository.GenderRepository;
import com.DisneyAlkemy.service.GenderService;

@Service
public class GenderServiceImpl implements GenderService {
    @Autowired
    private GenderMapper genderMapper;
    @Autowired
    private GenderRepository genderRepository;

    public GenderServiceImpl( @Lazy GenderMapper genderMapper, GenderRepository genderRepository) {
        this.genderMapper = genderMapper;
        this.genderRepository = genderRepository;
    }


    //Post
    @Override
    public GenderDTO save(GenderDTO gender) {

        GenderEntity genderEntity = genderMapper.genderDTO2Entity(gender);
        GenderEntity savedGender = genderRepository.save(genderEntity);
        GenderDTO result = genderMapper.genderEntity2DTO(savedGender);

        return result;
    }
}
