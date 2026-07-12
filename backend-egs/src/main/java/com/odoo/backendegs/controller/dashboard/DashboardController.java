package com.odoo.backendegs.controller.dashboard;

import com.odoo.backendegs.dto.response.ApiResponseDto;
import com.odoo.backendegs.dto.response.DashboardResponse;
import com.odoo.backendegs.service.dashboard.DashboardService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {

    private final DashboardService dashboardService;

    public DashboardController(DashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }

    @GetMapping("/{departmentId}")
    public ResponseEntity<ApiResponseDto<?>> getDepartmentDashboard(
            @PathVariable Long departmentId){

        DashboardResponse response =
                dashboardService.getDepartmentDashboard(departmentId);

        return new ResponseEntity<>(
                new ApiResponseDto<>(response,
                        "Dashboard fetched successfully.",
                        HttpStatus.OK),
                HttpStatus.OK);
    }

}