package com.odoo.backendegs.dto.response;

import com.odoo.backendegs.enums.social.CSRActivityStatus;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class CSRActivityResponse {

    private Long id;

    private String title;

    private String description;

    private Integer xpReward;

    private LocalDate startDate;

    private LocalDate endDate;

    private CSRActivityStatus status;

}