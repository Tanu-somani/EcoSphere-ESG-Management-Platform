package com.odoo.backendegs.dto.response;


import com.odoo.backendegs.enums.AuditStatus;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class AuditResponse {

    private Long id;

    private String title;

    private String description;

    private Long departmentId;

    private String departmentName;

    private LocalDate auditDate;

    private AuditStatus status;


}