package com.odoo.backendegs.dto.response;

import com.odoo.backendegs.enums.ComplianceStatus;
import com.odoo.backendegs.enums.Severity;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ComplianceIssueResponse {

    private Long id;

    private String title;

    private String description;

    private Severity severity;

    private ComplianceStatus status;

    private Long auditId;

    private Long departmentId;

    private LocalDateTime resolvedAt;

}