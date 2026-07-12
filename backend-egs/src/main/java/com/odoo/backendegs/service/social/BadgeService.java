package com.odoo.backendegs.service.social;

import com.odoo.backendegs.entity.social.badge.Badge;
import com.odoo.backendegs.entity.social.badge.EmployeeBadge;
import com.odoo.backendegs.repo.social.BadgeRepository;
import com.odoo.backendegs.repo.social.EmployeeBadgeRepository;
import com.odoo.backendegs.repo.social.XPTransactionRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BadgeService {

    private final BadgeRepository badgeRepo;
    private final EmployeeBadgeRepository employeeBadgeRepo;
    private final XPTransactionRepository xpTransactionRepo;

    public BadgeService(BadgeRepository badgeRepo,
                        EmployeeBadgeRepository employeeBadgeRepo,
                        XPTransactionRepository xpTransactionRepo) {

        this.badgeRepo = badgeRepo;
        this.employeeBadgeRepo = employeeBadgeRepo;
        this.xpTransactionRepo = xpTransactionRepo;
    }

    public void checkAndAssignBadges(String employeeName) {

        Integer totalXP = xpTransactionRepo.getEmployeeXP(employeeName);

        List<Badge> badges = badgeRepo.findByOrderByRequiredXpAsc();

        List<EmployeeBadge> earnedBadges =
                employeeBadgeRepo.findByEmployeeName(employeeName);

        for (Badge badge : badges) {

            boolean alreadyEarned = earnedBadges.stream()
                    .anyMatch(employeeBadge ->
                            employeeBadge.getBadge().getId().equals(badge.getId()));

            if (!alreadyEarned && totalXP >= badge.getRequiredXp()) {

                EmployeeBadge employeeBadge = EmployeeBadge.builder()
                        .employeeName(employeeName)
                        .badge(badge)
                        .earnedAt(LocalDateTime.now())
                        .build();

                employeeBadgeRepo.save(employeeBadge);
            }
        }
    }

    public List<EmployeeBadge> getEmployeeBadges(String employeeName) {

        return employeeBadgeRepo.findByEmployeeName(employeeName);

    }

}