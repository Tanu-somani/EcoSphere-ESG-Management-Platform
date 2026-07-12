package com.odoo.backendegs.repo.governance;

import com.odoo.backendegs.entity.governance.ComplianceIssue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ComplianceIssueRepository extends JpaRepository<ComplianceIssue, Long> {

    @Query("""
            SELECT COUNT(c)
            FROM ComplianceIssue c
            WHERE c.department.id = :departmentId
            AND c.status = com.odoo.backendegs.enums.ComplianceStatus.OPEN
            """)
    Integer getOpenIssues(@Param("departmentId") Long departmentId);

}