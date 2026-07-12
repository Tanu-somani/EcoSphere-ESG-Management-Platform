package com.odoo.backendegs.controller.environment;

import com.odoo.backendegs.dto.request.CreateResourceRequest;
import com.odoo.backendegs.dto.response.ApiResponseDto;
import com.odoo.backendegs.dto.response.ResourceResponse;
import com.odoo.backendegs.service.environment.ResourceService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/resources")
public class ResourceController {

    private final ResourceService resourceService;

    public ResourceController(ResourceService resourceService) {
        this.resourceService = resourceService;
    }

    @PostMapping
    public ResponseEntity<ApiResponseDto<?>> createResource(
            @Valid @RequestBody CreateResourceRequest request){

        ResourceResponse response =
                resourceService.createResource(request);

        return new ResponseEntity<>(
                new ApiResponseDto<>(
                        response,
                        "Resource created successfully.",
                        HttpStatus.CREATED
                ),
                HttpStatus.CREATED
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponseDto<?>> getResource(
            @PathVariable Long id){

        ResourceResponse response =
                resourceService.getResource(id);

        return new ResponseEntity<>(
                new ApiResponseDto<>(
                        response,
                        "Resource fetched successfully.",
                        HttpStatus.OK
                ),
                HttpStatus.OK
        );
    }

    @GetMapping
    public ResponseEntity<ApiResponseDto<?>> getAllResources(){

        List<ResourceResponse> response =
                resourceService.getAllResources();

        return new ResponseEntity<>(
                new ApiResponseDto<>(
                        response,
                        "Resources fetched successfully.",
                        HttpStatus.OK
                ),
                HttpStatus.OK
        );
    }

}
