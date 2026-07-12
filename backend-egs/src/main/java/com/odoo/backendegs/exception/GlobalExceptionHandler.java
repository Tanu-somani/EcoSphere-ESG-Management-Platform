package com.odoo.backendegs.exception;

import com.odoo.backendegs.dto.response.ApiResponseDto;
import com.odoo.backendegs.exception.exceptions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Collections;

@ControllerAdvice
public class GlobalExceptionHandler {



    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponseDto<?>> resourceNotFoundException(ResourceNotFoundException exception){

        return new ResponseEntity<>(
                new ApiResponseDto<>(Collections.emptyMap(),exception.getMessage(), HttpStatus.NOT_FOUND),
                HttpStatus.NOT_FOUND
        );

    }

}
