package com.odoo.backendegs.controller.social;

import com.odoo.backendegs.dto.request.CreateCSRActivityRequest;
import com.odoo.backendegs.dto.request.JoinCSRActivityRequest;
import com.odoo.backendegs.dto.response.ApiResponseDto;
import com.odoo.backendegs.dto.response.CSRActivityResponse;
import com.odoo.backendegs.dto.response.ParticipationResponse;
import com.odoo.backendegs.service.social.CSRService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/csr")
public class CSRController {

    private final CSRService csrService;

    public CSRController(CSRService csrService) {
        this.csrService = csrService;
    }

    @PostMapping
    public ResponseEntity<ApiResponseDto<?>> createActivity(
            @Valid @RequestBody CreateCSRActivityRequest request){

        CSRActivityResponse response = csrService.createActivity(request);

        return new ResponseEntity<>(
                new ApiResponseDto<>(response,
                        "CSR Activity created successfully.",
                        HttpStatus.CREATED),
                HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<ApiResponseDto<?>> getAllActivities(){

        List<CSRActivityResponse> response = csrService.getAllActivities();

        return new ResponseEntity<>(
                new ApiResponseDto<>(response,
                        "CSR Activities fetched successfully.",
                        HttpStatus.OK),
                HttpStatus.OK);
    }

    @PostMapping("/join")
    public ResponseEntity<ApiResponseDto<?>> joinActivity(
            @Valid @RequestBody JoinCSRActivityRequest request){

        ParticipationResponse response = csrService.joinActivity(request);

        return new ResponseEntity<>(
                new ApiResponseDto<>(response,
                        "Participation submitted successfully.",
                        HttpStatus.OK),
                HttpStatus.OK);
    }

    @PutMapping("/approve/{participationId}")
    public ResponseEntity<ApiResponseDto<?>> approveParticipation(
            @PathVariable Long participationId){

        ParticipationResponse response =
                csrService.approveParticipation(participationId);

        return new ResponseEntity<>(
                new ApiResponseDto<>(response,
                        "Participation approved successfully.",
                        HttpStatus.OK),
                HttpStatus.OK);
    }

    @PutMapping("/reject/{participationId}")
    public ResponseEntity<ApiResponseDto<?>> rejectParticipation(
            @PathVariable Long participationId){

        ParticipationResponse response =
                csrService.rejectParticipation(participationId);

        return new ResponseEntity<>(
                new ApiResponseDto<>(response,
                        "Participation rejected successfully.",
                        HttpStatus.OK),
                HttpStatus.OK);
    }

}