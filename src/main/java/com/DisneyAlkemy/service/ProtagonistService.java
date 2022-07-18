package com.DisneyAlkemy.service;



import com.DisneyAlkemy.dto.ProtagonistBasicDTO;
import com.DisneyAlkemy.dto.ProtagonistDTO;
import java.util.List;
import java.util.Set;

public interface ProtagonistService {

    ProtagonistDTO save(ProtagonistDTO dto);

    void delete(Long id);

    ProtagonistDTO putCharacter(Long id, ProtagonistDTO edit);


    List<ProtagonistDTO> getByFilters(String name, String image, Integer age, Set<Long> idMovie);

    List<ProtagonistBasicDTO> getAllBasics();
}
