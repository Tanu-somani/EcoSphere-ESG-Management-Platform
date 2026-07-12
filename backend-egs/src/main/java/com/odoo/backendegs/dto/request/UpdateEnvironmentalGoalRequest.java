package com.odoo.backendegs.dto.request;


import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.time.LocalDate;

@Data
public class UpdateEnvironmentalGoalRequest {

    @NotBlank(message = "Goal title is required.")
    private String title;

    @NotBlank(message = "Goal description is required.")
    private String description;

    @NotNull(message = "Target carbon is required.")
    @Positive(message = "Target carbon must be greater than 0.")
    private Double targetCarbon;

    @NotNull(message = "Current carbon is required.")
    @Positive(message = "Current carbon must be greater than 0.")
    private Double currentCarbon;

    @NotNull(message = "Target date is required.")
    @Future(message = "Target date must be in the future.")
    private LocalDate targetDate;

}