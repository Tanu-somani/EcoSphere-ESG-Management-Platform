package com.odoo.backendegs.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BadgeResponse {

    private Long id;

    private String name;

    private String description;

    private Integer requiredXp;

    private String badgeIcon;

}