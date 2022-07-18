package com.DisneyAlkemy.controller;


import com.DisneyAlkemy.dto.ProtagonistBasicDTO;
import com.DisneyAlkemy.dto.ProtagonistDTO;
import com.DisneyAlkemy.service.ProtagonistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
/**
 *
 * @author Guille
 */
@RestController
@RequestMapping("characters")
public class ProtagonistController {


    private ProtagonistService protagonistService;

    public ProtagonistController(@Autowired @Lazy ProtagonistService protagonistService) {
        this.protagonistService = protagonistService;
    }


    @PostMapping
    public ResponseEntity<ProtagonistDTO> save(@RequestBody ProtagonistDTO character) {

        ProtagonistDTO savedCharacter = protagonistService.save(character);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedCharacter);
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        this.protagonistService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


    @PutMapping("/{id}")
    public ResponseEntity<ProtagonistDTO> put(@PathVariable Long id, @RequestBody ProtagonistDTO edit) {
        ProtagonistDTO putCharacter = protagonistService.putCharacter(id, edit);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(putCharacter);
    }


    //Get for a List with image and name of characters

    @GetMapping("/all")
    public ResponseEntity<List<ProtagonistBasicDTO>> getAllBasic() {

        List<ProtagonistBasicDTO> basicsCharacters = protagonistService.getAllBasics();

        return ResponseEntity.status(HttpStatus.OK).body(basicsCharacters);
    }

    //Get for all atributes and filters
    @GetMapping
    public ResponseEntity<List<ProtagonistDTO>> getDetailsByFilters (
      @RequestParam(required = false) String name,
      @RequestParam(required = false) String image,
      @RequestParam(required = false) Integer age,
      @RequestParam(required = false) Set<Long> IdMovie)
    {
       List<ProtagonistDTO> charactersListDto = this.protagonistService.getByFilters(name, image, age, IdMovie);

       return ResponseEntity.status(HttpStatus.OK).body(charactersListDto);
    }







}
