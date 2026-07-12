package com.odoo.backendegs.dto.response;

import com.odoo.backendegs.enums.social.ParticipationStatus;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ParticipationResponse {

    private Long id;

    private String employeeName;

    private Long departmentId;

    private String departmentName;

    private Long csrActivityId;

    private String csrActivityTitle;

    private String proofUrl;

    private String remarks;

    private ParticipationStatus status;

    private LocalDateTime joinedAt;

    private LocalDateTime reviewedAt;

}