package com.odoo.backendegs.service.esg;

import com.odoo.backendegs.engine.OverallScoreEngine;
import com.odoo.backendegs.entity.esg.DepartmentScore;
import com.odoo.backendegs.exception.exceptions.ResourceNotFoundException;
import com.odoo.backendegs.repo.environmental.DepartmentScoreRepo;
import org.springframework.stereotype.Service;

@Service
public class EsgScoreService {

    private final DepartmentScoreRepo departmentScoreRepo;
    private final OverallScoreEngine overallScoreEngine;

    public EsgScoreService(DepartmentScoreRepo departmentScoreRepo,
                           OverallScoreEngine overallScoreEngine) {

        this.departmentScoreRepo = departmentScoreRepo;
        this.overallScoreEngine = overallScoreEngine;
    }

    public void updateDepartmentScore(Long departmentId) {

        DepartmentScore departmentScore = departmentScoreRepo
                .findByDepartmentId(departmentId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Department Score not found"));

        double overallScore =
                overallScoreEngine.calculateOverallScore(
                        departmentScore.getEnvironmentScore(),
                        departmentScore.getSocialScore(),
                        departmentScore.getGovernanceScore()
                );

        departmentScore.setOverallScore(overallScore);

        departmentScoreRepo.save(departmentScore);
    }
}