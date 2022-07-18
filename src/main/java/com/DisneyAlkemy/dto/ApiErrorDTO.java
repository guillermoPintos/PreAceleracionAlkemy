package com.DisneyAlkemy.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.List;
/**
 *
 * @author Guille
 */
@Data
@AllArgsConstructor
public class ApiErrorDTO {

    private HttpStatus status;
    private String message;
    private List<String> errors;
}
