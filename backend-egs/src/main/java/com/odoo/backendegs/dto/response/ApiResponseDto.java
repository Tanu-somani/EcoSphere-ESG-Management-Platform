package com.odoo.backendegs.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
public class ApiResponseDto<T> {

    private T data;
    private String message;
    private HttpStatus statusCode;


}
