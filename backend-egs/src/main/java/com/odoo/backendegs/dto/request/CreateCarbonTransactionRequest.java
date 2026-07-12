package com.odoo.backendegs.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Data
@Getter
@Setter
public class CreateCarbonTransactionRequest {

    @NotNull(message = "Department ID is required.")
    private Long departmentId;

    @NotNull(message = "Resource ID is required.")
    private Long resourceId;

    @NotNull(message = "Quantity is required.")
    @Positive(message = "Quantity must be greater than zero.")
    private Double quantity;

    @NotNull(message = "Transaction date is required.")
    private LocalDate transactionDate;

    private String notes;


}
