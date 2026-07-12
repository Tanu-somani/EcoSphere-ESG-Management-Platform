package com.odoo.backendegs.dto.response;

import com.odoo.backendegs.enums.GoalStatus;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class EnvironmentalGoalResponse {

    private Long id;

    private Long departmentId;

    private String departmentName;

    private String title;

    private String description;

    private Double targetCarbon;

    private Double currentCarbon;

    private Double progressPercentage;

    private GoalStatus status;

    private LocalDate targetDate;

}
