package com.DisneyAlkemy.service.impl;



import com.DisneyAlkemy.dto.ProtagonistBasicDTO;
import com.DisneyAlkemy.dto.ProtagonistDTO;
import com.DisneyAlkemy.dto.ProtagonistFiltersDTO;
import com.DisneyAlkemy.entity.ProtagonistEntity;
import com.DisneyAlkemy.exception.ParamNotFound;
import com.DisneyAlkemy.mapper.ProtagonistMapper;
import com.DisneyAlkemy.repository.ProtagonistRepository;
import com.DisneyAlkemy.repository.specifications.ProtagonistSpecification;
import com.DisneyAlkemy.service.ProtagonistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class ProtagonistServiceImpl implements ProtagonistService {

    @Autowired
    private ProtagonistMapper protagonistMapper;
    @Autowired
    private ProtagonistRepository protagonistRepository;
    @Autowired
    private ProtagonistSpecification protagonistSpecification;

    public ProtagonistServiceImpl(@Lazy ProtagonistMapper protagonistMapper, @Lazy ProtagonistRepository protagonistRepository,@Lazy ProtagonistSpecification protagonistSpecification) {
        this.protagonistMapper = protagonistMapper;
        this.protagonistRepository = protagonistRepository;
        this.protagonistSpecification = protagonistSpecification;
    }

    //post
    public ProtagonistDTO save(ProtagonistDTO dto){
        ProtagonistEntity protagonistEntity = protagonistMapper.protagonistDTO2Entity(dto);
        ProtagonistEntity protagonistSaved = protagonistRepository.save(protagonistEntity);
        ProtagonistDTO resultado = protagonistMapper.protagonistEntity2DTO(protagonistSaved, false);

        return resultado;
    }

    //Delete
    public void delete(Long id){
        protagonistRepository.deleteById(id);
    }
    //Put
    @Override
    public ProtagonistDTO putCharacter(Long id, ProtagonistDTO edit) {

        ProtagonistEntity savedCharacter = this.characterEdit(id);
        savedCharacter.setWeight(edit.getWeight());
        savedCharacter.setName(edit.getName());
        savedCharacter.setImage(edit.getImage());
        savedCharacter.setHistory(edit.getHistory());
        savedCharacter.setAge(edit.getAge());

        ProtagonistEntity putCharacter = protagonistRepository.save(savedCharacter);
        ProtagonistDTO savedDTO = protagonistMapper.protagonistEntity2DTO(putCharacter, false);
        return savedDTO;
    }


    private ProtagonistEntity characterEdit(Long id) {
        Optional<ProtagonistEntity> protagonistEntity = protagonistRepository.findById(id);
        if (!protagonistEntity.isPresent()){
            throw new ParamNotFound("Id no valido");
        }
        return protagonistEntity.get();
    }

    @Override
    public List<ProtagonistBasicDTO> getAllBasics() {

        List<ProtagonistEntity> entityList = protagonistRepository.findAll();
        List<ProtagonistBasicDTO> resultado = protagonistMapper.protagonistEntityList2DTOListBasic(entityList);

        return resultado;
    }

    //Filtros
    public List<ProtagonistDTO> getByFilters(String name, String image, Integer age, Set<Long> Idmovie){

        ProtagonistFiltersDTO filtersDTO = new ProtagonistFiltersDTO(name, image, age, Idmovie);

        List<ProtagonistEntity> entities = this.protagonistRepository.findAll(this.protagonistSpecification.getByFilters(filtersDTO));
        List<ProtagonistDTO> dtos = this.protagonistMapper.protagonistEntityList2DTOList(entities, true);
        return dtos;
        }




}
