package com.odoo.backendegs.dto.request;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.time.LocalDate;

@Data
public class CreateEnvironmentalGoalRequest {

    @NotNull(message = "Department ID is required.")
    private Long departmentId;

    @NotBlank(message = "Goal title is required.")
    private String title;

    @NotBlank(message = "Goal description is required.")
    private String description;

    @NotNull(message = "Target carbon is required.")
    @Positive(message = "Target carbon must be greater than 0.")
    private Double targetCarbon;

    @NotNull(message = "Target date is required.")
    @Future(message = "Target date must be in the future.")
    private LocalDate targetDate;

}
