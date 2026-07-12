package com.odoo.backendegs.service.social;

import com.odoo.backendegs.entity.social.CSRActivity;
import com.odoo.backendegs.entity.social.Participation;
import com.odoo.backendegs.entity.social.XPTransaction;
import com.odoo.backendegs.enums.social.XPReason;
import com.odoo.backendegs.exception.exceptions.ResourceNotFoundException;
import com.odoo.backendegs.repo.social.ParticipationRepository;
import com.odoo.backendegs.repo.social.XPTransactionRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class XPService {

    private final XPTransactionRepository xpTransactionRepo;
    private final ParticipationRepository participationRepo;
    private final BadgeService badgeService;

    public XPService(XPTransactionRepository xpTransactionRepo,
                     ParticipationRepository participationRepo,
                     BadgeService badgeService) {

        this.xpTransactionRepo = xpTransactionRepo;
        this.participationRepo = participationRepo;
        this.badgeService = badgeService;
    }

    public void awardXP(Long participationId) {

        Participation participation = participationRepo.findById(participationId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Participation not found"));

        CSRActivity activity = participation.getCsrActivity();

        XPTransaction transaction = XPTransaction.builder()
                .employeeName(participation.getEmployeeName())
                .department(participation.getDepartment())
                .participation(participation)
                .xpEarned(activity.getXpReward())
                .reason(XPReason.CSR_ACTIVITY)
                .remarks("XP awarded for CSR Activity")
                .awardedAt(LocalDateTime.now())
                .build();

        xpTransactionRepo.save(transaction);

        badgeService.checkAndAssignBadges(
                participation.getEmployeeName()
        );
    }

    public Integer getEmployeeXP(String employeeName) {

        return xpTransactionRepo.getEmployeeXP(employeeName);

    }

    public Integer getDepartmentXP(Long departmentId) {

        return xpTransactionRepo.getTotalDepartmentXP(departmentId);

    }

}