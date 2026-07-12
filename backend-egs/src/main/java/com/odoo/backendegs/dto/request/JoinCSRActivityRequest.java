package com.odoo.backendegs.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class JoinCSRActivityRequest {

    @NotBlank(message = "Employee name is required.")
    private String employeeName;

    @NotNull(message = "Department ID is required.")
    private Long departmentId;

    @NotNull(message = "CSR Activity ID is required.")
    private Long csrActivityId;

    @NotBlank(message = "Proof URL is required.")
    private String proofUrl;

    private String remarks;

}