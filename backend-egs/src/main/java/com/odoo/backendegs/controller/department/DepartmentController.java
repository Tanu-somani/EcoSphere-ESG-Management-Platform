package com.odoo.backendegs.controller.department;

import com.odoo.backendegs.dto.request.CreateDepartmentRequest;
import com.odoo.backendegs.dto.response.ApiResponseDto;
import com.odoo.backendegs.dto.response.DepartmentResponse;
import com.odoo.backendegs.service.department.DepartmentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/departments")
public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @PostMapping
    public ResponseEntity<ApiResponseDto<?>> createDepartment(
            @Valid @RequestBody CreateDepartmentRequest request) {

        DepartmentResponse response =
                departmentService.createDepartment(request);

        return new ResponseEntity<>(
                new ApiResponseDto<>(response,
                        "Department created successfully.",
                        HttpStatus.CREATED),
                HttpStatus.CREATED
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponseDto<?>> getDepartment(
            @PathVariable Long id) {

        DepartmentResponse response =
                departmentService.getDepartment(id);

        return new ResponseEntity<>(
                new ApiResponseDto<>(response,
                        "Department fetched successfully.",
                        HttpStatus.OK),
                HttpStatus.OK
        );
    }

    @GetMapping
    public ResponseEntity<ApiResponseDto<?>> getAllDepartments() {

        List<DepartmentResponse> response =
                departmentService.getAllDepartments();

        return new ResponseEntity<>(
                new ApiResponseDto<>(response,
                        "Departments fetched successfully.",
                        HttpStatus.OK),
                HttpStatus.OK
        );
    }

}