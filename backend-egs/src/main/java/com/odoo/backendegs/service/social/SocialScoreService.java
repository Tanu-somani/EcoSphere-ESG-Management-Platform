package com.odoo.backendegs.service.social;

import com.odoo.backendegs.engine.SocialScoreEngine;
import com.odoo.backendegs.entity.esg.DepartmentScore;
import com.odoo.backendegs.exception.exceptions.ResourceNotFoundException;
import com.odoo.backendegs.repo.environmental.DepartmentScoreRepo;
import com.odoo.backendegs.repo.social.XPTransactionRepository;
import org.springframework.stereotype.Service;

@Service
public class SocialScoreService {

    private final XPTransactionRepository xpTransactionRepo;
    private final DepartmentScoreRepo departmentScoreRepo;
    private final SocialScoreEngine socialScoreEngine;

    public SocialScoreService(XPTransactionRepository xpTransactionRepo,
                              DepartmentScoreRepo departmentScoreRepo,
                              SocialScoreEngine socialScoreEngine) {

        this.xpTransactionRepo = xpTransactionRepo;
        this.departmentScoreRepo = departmentScoreRepo;
        this.socialScoreEngine = socialScoreEngine;
    }

    public void updateDepartmentSocialScore(Long departmentId) {

        DepartmentScore departmentScore = departmentScoreRepo
                .findByDepartmentId(departmentId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Department Score not found"));

        Integer totalXP =
                xpTransactionRepo.getTotalDepartmentXP(departmentId);

        double socialScore =
                socialScoreEngine.calculateScore(totalXP);

        departmentScore.setSocialScore(socialScore);

        departmentScoreRepo.save(departmentScore);
    }

}