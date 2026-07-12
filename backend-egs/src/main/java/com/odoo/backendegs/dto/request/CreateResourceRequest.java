package com.odoo.backendegs.dto.request;

import com.odoo.backendegs.enums.ResourceCategory;
import com.odoo.backendegs.enums.UnitType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateResourceRequest {

    @NotBlank(message = "Resource name is required.")
    private String name;

    @NotNull(message = "Category is required.")
    private ResourceCategory category;

    @NotNull(message = "Unit type is required.")
    private UnitType unit;

    @NotBlank(message = "Description is required.")
    private String description;

    @NotNull(message = "Emission factor is required.")
    private Double emissionFactor;

}