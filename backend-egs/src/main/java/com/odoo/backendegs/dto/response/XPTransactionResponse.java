package com.odoo.backendegs.dto.response;

import com.odoo.backendegs.enums.social.XPReason;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class XPTransactionResponse {

    private Long id;

    private String employeeName;

    private Long departmentId;

    private Integer xpEarned;

    private XPReason reason;

    private String remarks;

    private LocalDateTime awardedAt;

}