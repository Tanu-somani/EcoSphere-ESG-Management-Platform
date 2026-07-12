package com.odoo.backendegs.dto.request;

import com.odoo.backendegs.enums.social.CSRActivityStatus;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class CreateCSRActivityRequest {

    @NotBlank(message = "Title is required.")
    private String title;

    @NotBlank(message = "Description is required.")
    private String description;

    @NotNull(message = "XP Reward is required.")
    @Min(value = 1, message = "XP Reward must be at least 1.")
    private Integer xpReward;

    @NotNull(message = "Start date is required.")
    private LocalDate startDate;

    @NotNull(message = "End date is required.")
    @Future(message = "End date must be in the future.")
    private LocalDate endDate;

    private CSRActivityStatus status;

}

