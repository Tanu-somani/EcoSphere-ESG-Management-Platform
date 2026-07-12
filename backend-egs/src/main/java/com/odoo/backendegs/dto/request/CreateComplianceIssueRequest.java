package com.odoo.backendegs.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import javax.print.attribute.standard.Severity;

@Data
public class CreateComplianceIssueRequest {

    @NotNull
    private Long auditId;

    @NotNull
    private Long departmentId;

    @NotBlank
    private String title;

    @NotBlank
    private String description;

    @NotNull
    private Severity severity;

}
