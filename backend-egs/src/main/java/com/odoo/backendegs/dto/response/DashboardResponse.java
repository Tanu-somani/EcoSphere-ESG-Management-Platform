package com.odoo.backendegs.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DashboardResponse {

    private Long departmentId;

    private String departmentName;

    private Double environmentalScore;

    private Double socialScore;

    private Double governanceScore;

    private Double overallScore;

    private Double totalCarbonEmission;

    private Integer totalDepartmentXP;

    private Integer openComplianceIssues;

}