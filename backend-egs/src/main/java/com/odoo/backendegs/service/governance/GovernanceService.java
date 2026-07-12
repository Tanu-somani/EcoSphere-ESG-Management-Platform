package com.odoo.backendegs.service.governance;

import com.odoo.backendegs.engine.GovernanceScoreEngine;
import com.odoo.backendegs.entity.esg.DepartmentScore;
import com.odoo.backendegs.exception.exceptions.ResourceNotFoundException;
import com.odoo.backendegs.repo.environmental.DepartmentScoreRepo;
import com.odoo.backendegs.repo.governance.ComplianceIssueRepository;
import com.odoo.backendegs.service.esg.EsgScoreService;
import org.springframework.stereotype.Service;

@Service
public class GovernanceService {

    private final ComplianceIssueRepository complianceIssueRepository;
    private final DepartmentScoreRepo departmentScoreRepo;
    private final GovernanceScoreEngine governanceScoreEngine;
    private final EsgScoreService esgScoreService;

    public GovernanceService(
            ComplianceIssueRepository complianceIssueRepository,
            DepartmentScoreRepo departmentScoreRepo,
            GovernanceScoreEngine governanceScoreEngine,
            EsgScoreService esgScoreService) {

        this.complianceIssueRepository = complianceIssueRepository;
        this.departmentScoreRepo = departmentScoreRepo;
        this.governanceScoreEngine = governanceScoreEngine;
        this.esgScoreService = esgScoreService;
    }
    public void updateDepartmentGovernanceScore(Long departmentId) {

        Integer openIssues =
                complianceIssueRepository.getOpenIssues(departmentId);

        if (openIssues == null) {
            openIssues = 0;
        }

        double governanceScore =
                governanceScoreEngine.calculateScore(openIssues);

        DepartmentScore departmentScore =
                departmentScoreRepo.findByDepartmentId(departmentId)
                        .orElseThrow(() ->
                                new ResourceNotFoundException("Department Score not found"));

        departmentScore.setGovernanceScore(governanceScore);

        departmentScoreRepo.save(departmentScore);
    }
}
