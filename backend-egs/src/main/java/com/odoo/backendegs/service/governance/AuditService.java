package com.odoo.backendegs.service.governance;

import com.odoo.backendegs.dto.request.CreateAuditRequest;
import com.odoo.backendegs.dto.request.CreateComplianceIssueRequest;
import com.odoo.backendegs.dto.response.AuditResponse;
import com.odoo.backendegs.dto.response.ComplianceIssueResponse;
import com.odoo.backendegs.entity.department.Department;
import com.odoo.backendegs.entity.governance.Audit;
import com.odoo.backendegs.entity.governance.ComplianceIssue;
import com.odoo.backendegs.enums.AuditStatus;
import com.odoo.backendegs.enums.ComplianceStatus;
import com.odoo.backendegs.exception.exceptions.ResourceNotFoundException;
import com.odoo.backendegs.repo.department.DepartmentRepo;
import com.odoo.backendegs.repo.governance.AuditRepository;
import com.odoo.backendegs.repo.governance.ComplianceIssueRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AuditService {

    private final AuditRepository auditRepository;
    private final ComplianceIssueRepository complianceIssueRepository;
    private final DepartmentRepo departmentRepo;
    private final GovernanceService governanceService;
    private final ModelMapper modelMapper;

    public AuditService(
            AuditRepository auditRepository,
            ComplianceIssueRepository complianceIssueRepository,
            DepartmentRepo departmentRepo,
            GovernanceService governanceService,
            ModelMapper modelMapper) {

        this.auditRepository = auditRepository;
        this.complianceIssueRepository = complianceIssueRepository;
        this.departmentRepo = departmentRepo;
        this.governanceService = governanceService;
        this.modelMapper = modelMapper;
    }

    public AuditResponse createAudit(CreateAuditRequest request) {

        Department department = departmentRepo.findById(request.getDepartmentId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Department not found"));

        Audit audit = modelMapper.map(request, Audit.class);

        audit.setDepartment(department);
        audit.setStatus(AuditStatus.PENDING);

        auditRepository.save(audit);

        return modelMapper.map(audit, AuditResponse.class);
    }

    public ComplianceIssueResponse createComplianceIssue(CreateComplianceIssueRequest request) {

        Audit audit = auditRepository.findById(request.getAuditId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Audit not found"));

        Department department = departmentRepo.findById(request.getDepartmentId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Department not found"));

        ComplianceIssue issue = modelMapper.map(request, ComplianceIssue.class);

        issue.setAudit(audit);
        issue.setDepartment(department);
        issue.setStatus(ComplianceStatus.OPEN);

        complianceIssueRepository.save(issue);

        governanceService.updateDepartmentGovernanceScore(department.getId());

        return modelMapper.map(issue, ComplianceIssueResponse.class);
    }

    public ComplianceIssueResponse resolveIssue(Long issueId) {

        ComplianceIssue issue = complianceIssueRepository.findById(issueId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Compliance Issue not found"));

        issue.setStatus(ComplianceStatus.RESOLVED);
        issue.setResolvedAt(LocalDateTime.now());

        complianceIssueRepository.save(issue);

        governanceService.updateDepartmentGovernanceScore(
                issue.getDepartment().getId()
        );

        return modelMapper.map(issue, ComplianceIssueResponse.class);
    }

    public List<AuditResponse> getAllAudits() {

        return auditRepository.findAll()
                .stream()
                .map(audit -> modelMapper.map(audit, AuditResponse.class))
                .toList();
    }

}