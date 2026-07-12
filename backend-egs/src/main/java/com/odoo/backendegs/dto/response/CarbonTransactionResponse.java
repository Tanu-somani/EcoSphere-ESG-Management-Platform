package com.odoo.backendegs.dto.response;

import lombok.Data;

import java.time.LocalDate;

@Data
public class CarbonTransactionResponse {

    private Long id;

    private Long departmentId;
    private String departmentName;

    private Long resourceId;
    private String resourceName;

    private Double quantity;

    private Double emissionFactorUsed;

    private Double carbonGenerated;

    private LocalDate transactionDate;

    private String notes;
}
