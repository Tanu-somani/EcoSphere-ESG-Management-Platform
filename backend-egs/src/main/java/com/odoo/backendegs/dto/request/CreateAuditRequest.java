package com.odoo.backendegs.dto.request;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class CreateAuditRequest {

    @NotBlank
    private String title;

    @NotBlank
    private String description;

    @NotNull
    private Long departmentId;

    @NotNull
    private LocalDate auditDate;

}