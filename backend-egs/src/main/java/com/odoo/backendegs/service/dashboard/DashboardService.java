package com.odoo.backendegs.service.dashboard;

import com.odoo.backendegs.dto.response.DashboardResponse;
import com.odoo.backendegs.entity.department.Department;
import com.odoo.backendegs.entity.esg.DepartmentScore;
import com.odoo.backendegs.exception.exceptions.ResourceNotFoundException;
import com.odoo.backendegs.repo.department.DepartmentRepo;
import com.odoo.backendegs.repo.environmental.CarbonTransactionRepository;
import com.odoo.backendegs.repo.governance.ComplianceIssueRepository;
import com.odoo.backendegs.repo.social.XPTransactionRepository;
import org.springframework.stereotype.Service;

@Service
public class DashboardService {

    private final DepartmentRepo departmentRepo;
    private final CarbonTransactionRepository carbonTransactionRepository;
    private final XPTransactionRepository xpTransactionRepo;
    private final ComplianceIssueRepository complianceIssueRepository;

    public DashboardService(DepartmentRepo departmentRepo,
                            CarbonTransactionRepository carbonTransactionRepository,
                            XPTransactionRepository xpTransactionRepo,
                            ComplianceIssueRepository complianceIssueRepository) {
        this.departmentRepo = departmentRepo;
        this.carbonTransactionRepository = carbonTransactionRepository;
        this.xpTransactionRepo = xpTransactionRepo;
        this.complianceIssueRepository = complianceIssueRepository;
    }

    public DashboardResponse getDepartmentDashboard(Long departmentId) {

        Department department = departmentRepo.findById(departmentId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Department " + departmentId + " not found"));

        DepartmentScore score = department.getDepartmentScore();

        Double totalCarbon =
                carbonTransactionRepository.getTotalCarbonEmission(departmentId);

        Integer totalXP =
                xpTransactionRepo.getTotalDepartmentXP(departmentId);

        Integer openIssues =
                complianceIssueRepository.getOpenIssues(departmentId);

        if (totalCarbon == null) totalCarbon = 0.0;
        if (totalXP == null) totalXP = 0;
        if (openIssues == null) openIssues = 0;

        return DashboardResponse.builder()
                .departmentId(department.getId())
                .departmentName(department.getName())
                .environmentalScore(score.getEnvironmentScore())
                .socialScore(score.getSocialScore())
                .governanceScore(score.getGovernanceScore())
                .overallScore(score.getOverallScore())
                .totalCarbonEmission(totalCarbon)
                .totalDepartmentXP(totalXP)
                .openComplianceIssues(openIssues)
                .build();
    }

}