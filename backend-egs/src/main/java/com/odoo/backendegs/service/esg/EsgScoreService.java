package com.odoo.backendegs.service.esg;

import com.odoo.backendegs.engine.EnvironmentalScoreEngine;
import com.odoo.backendegs.engine.GovernanceScoreEngine;
import com.odoo.backendegs.engine.OverallScoreEngine;
import com.odoo.backendegs.engine.SocialScoreEngine;
import com.odoo.backendegs.entity.department.Department;
import com.odoo.backendegs.entity.esg.DepartmentScore;
import com.odoo.backendegs.exception.exceptions.ResourceNotFoundException;
import com.odoo.backendegs.repo.department.DepartmentRepo;
import com.odoo.backendegs.repo.environmental.CarbonTransactionRepository;
import org.springframework.stereotype.Service;

@Service
public class EsgScoreService {

    private final EnvironmentalScoreEngine environmentalScoreEngine;
    private final GovernanceScoreEngine governanceScoreEngine;
    private final OverallScoreEngine overallScoreEngine;
    private final SocialScoreEngine socialScoreEngine;
    private final DepartmentRepo departmentRepo;
    private final CarbonTransactionRepository carbonTransactionRepository;

    public EsgScoreService(EnvironmentalScoreEngine environmentalScoreEngine, GovernanceScoreEngine governanceScoreEngine, OverallScoreEngine overallScoreEngine, SocialScoreEngine socialScoreEngine, DepartmentRepo departmentRepo, CarbonTransactionRepository carbonTransactionRepository) {
        this.environmentalScoreEngine = environmentalScoreEngine;
        this.governanceScoreEngine = governanceScoreEngine;
        this.overallScoreEngine = overallScoreEngine;
        this.socialScoreEngine = socialScoreEngine;
        this.departmentRepo = departmentRepo;
        this.carbonTransactionRepository = carbonTransactionRepository;
    }

    public void updateDepartmentScore(Long departmentId){

        Department department = departmentRepo.findById(departmentId)
                .orElseThrow(() -> new ResourceNotFoundException("Department " + departmentId + " not found"));

        DepartmentScore departmentScore = department.getDepartmentScore();

        if ( departmentScore == null){

            departmentScore = new DepartmentScore();

        }

        Double totalCarbon =
                carbonTransactionRepository.getTotalCarbonEmission(departmentId);


        double environmentalScore = environmentalScoreEngine
                .calculateScore(totalCarbon);

        double socialScore =
                socialScoreEngine.calculateScore();

        double governanceScore =
                governanceScoreEngine.calculateScore();

        double overallScore =
                overallScoreEngine.calculateOverallScore(
                        environmentalScore,
                        socialScore,
                        governanceScore
                );

        departmentScore.setEnvironmentScore(environmentalScore);
        departmentScore.setSocialScore(socialScore);
        departmentScore.setGovernanceScore(governanceScore);
        departmentScore.setOverallScore(overallScore);

        departmentRepo.save(department);
    }

}
