package com.odoo.backendegs.dto.response;

import com.odoo.backendegs.enums.ResourceCategory;
import com.odoo.backendegs.enums.UnitType;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResourceResponse {

    private Long id;

    private String name;

    private ResourceCategory category;

    private UnitType unit;

    private String description;

    private Double emissionFactor;

}