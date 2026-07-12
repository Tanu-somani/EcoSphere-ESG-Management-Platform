package com.odoo.backendegs.controller.governance;

import com.odoo.backendegs.dto.request.CreateAuditRequest;
import com.odoo.backendegs.dto.request.CreateComplianceIssueRequest;
import com.odoo.backendegs.dto.response.ApiResponseDto;
import com.odoo.backendegs.dto.response.AuditResponse;
import com.odoo.backendegs.dto.response.ComplianceIssueResponse;
import com.odoo.backendegs.service.governance.AuditService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/audit")
public class AuditController {

    private final AuditService auditService;

    public AuditController(AuditService auditService) {
        this.auditService = auditService;
    }

    @PostMapping
    public ResponseEntity<ApiResponseDto<?>> createAudit(
            @Valid @RequestBody CreateAuditRequest request){

        AuditResponse response = auditService.createAudit(request);

        return new ResponseEntity<>(
                new ApiResponseDto<>(response,
                        "Audit created successfully.",
                        HttpStatus.CREATED),
                HttpStatus.CREATED);
    }

    @PostMapping("/issue")
    public ResponseEntity<ApiResponseDto<?>> createIssue(
            @Valid @RequestBody CreateComplianceIssueRequest request){

        ComplianceIssueResponse response =
                auditService.createComplianceIssue(request);

        return new ResponseEntity<>(
                new ApiResponseDto<>(response,
                        "Compliance issue created successfully.",
                        HttpStatus.CREATED),
                HttpStatus.CREATED);
    }

    @PutMapping("/issue/{issueId}/resolve")
    public ResponseEntity<ApiResponseDto<?>> resolveIssue(
            @PathVariable Long issueId){

        ComplianceIssueResponse response =
                auditService.resolveIssue(issueId);

        return new ResponseEntity<>(
                new ApiResponseDto<>(response,
                        "Issue resolved successfully.",
                        HttpStatus.OK),
                HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<ApiResponseDto<?>> getAllAudits(){

        List<AuditResponse> response =
                auditService.getAllAudits();

        return new ResponseEntity<>(
                new ApiResponseDto<>(response,
                        "Audits fetched successfully.",
                        HttpStatus.OK),
                HttpStatus.OK);
    }

}