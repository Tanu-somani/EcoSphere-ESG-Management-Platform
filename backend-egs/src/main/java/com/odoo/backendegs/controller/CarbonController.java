package com.odoo.backendegs.controller;

import com.odoo.backendegs.dto.request.CreateCarbonTransactionRequest;
import com.odoo.backendegs.dto.response.ApiResponseDto;
import com.odoo.backendegs.dto.response.CarbonTransactionResponse;
import com.odoo.backendegs.service.environment.CarbonTransactionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/carbon/")
public class CarbonController {

    private final CarbonTransactionService carbonTransactionService;

    public CarbonController(CarbonTransactionService carbonTransactionService) {
        this.carbonTransactionService = carbonTransactionService;
    }


    @PostMapping("/")
    public ResponseEntity<ApiResponseDto<?>> saveTransaction(@RequestBody CreateCarbonTransactionRequest request){

        CarbonTransactionResponse response = carbonTransactionService.createCarbonTransaction(request);

        return new ResponseEntity<>(
            new ApiResponseDto<>(response,"Transaction Passed", HttpStatus.OK), HttpStatus.OK
        );


    }

    @GetMapping
    public ResponseEntity<ApiResponseDto<?>> getAllTransactions() {

        List<CarbonTransactionResponse> response =
                carbonTransactionService.getAllCarbonTransactions();

        return new ResponseEntity<>(
                new ApiResponseDto<>(
                        response,
                        "Carbon transactions fetched successfully.",
                        HttpStatus.OK
                ),
                HttpStatus.OK
        );
    }



    @GetMapping("/{id}")
    public ResponseEntity<ApiResponseDto<?>> getTransaction(
            @PathVariable Long id) {

        CarbonTransactionResponse response =
                carbonTransactionService.getCarbonTransaction(id);

        return new ResponseEntity<>(
                new ApiResponseDto<>(
                        response,
                        "Carbon transaction fetched successfully.",
                        HttpStatus.OK
                ),
                HttpStatus.OK
        );
    }


    @GetMapping("/department/{departmentId}")
    public ResponseEntity<ApiResponseDto<?>> getDepartmentTransactions(
            @PathVariable Long departmentId) {

        List<CarbonTransactionResponse> response =
                carbonTransactionService.getDepartmentTransactions(departmentId);

        return new ResponseEntity<>(
                new ApiResponseDto<>(
                        response,
                        "Department carbon transactions fetched successfully.",
                        HttpStatus.OK
                ),
                HttpStatus.OK
        );
    }

    @GetMapping("/department/{departmentId}/total")
    public ResponseEntity<ApiResponseDto<?>> getDepartmentTotalCarbon(
            @PathVariable Long departmentId) {

        Double response =
                carbonTransactionService.getDepartmentTotalCarbon(departmentId);

        return new ResponseEntity<>(
                new ApiResponseDto<>(
                        response,
                        "Department total carbon emission fetched successfully.",
                        HttpStatus.OK
                ),
                HttpStatus.OK
        );
    }


}
