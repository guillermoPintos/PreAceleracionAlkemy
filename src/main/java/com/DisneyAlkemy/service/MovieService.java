
package com.DisneyAlkemy.service;

import com.DisneyAlkemy.dto.MovieBasicDTO;
import com.DisneyAlkemy.dto.MovieDTO;
import java.util.List;
import java.util.Set;



/**
 *
 * @author Guille
 */
public interface MovieService {

    MovieDTO save(MovieDTO movie);

    void delete(Long id);

    MovieDTO putMovie(Long id, MovieDTO edit);

    List<MovieDTO> getByFilters(String title, String image, String dateCreation, Set<Long> gender, String order);

    List<MovieBasicDTO> getAllBasics();
}
