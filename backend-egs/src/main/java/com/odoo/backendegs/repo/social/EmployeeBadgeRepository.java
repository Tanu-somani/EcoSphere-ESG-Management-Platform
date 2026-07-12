package com.odoo.backendegs.repo.social;

import com.odoo.backendegs.entity.social.badge.Badge;
import com.odoo.backendegs.entity.social.badge.EmployeeBadge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeBadgeRepository extends JpaRepository<EmployeeBadge, Long> {

    List<EmployeeBadge> findByEmployeeName(String employeeName);

}