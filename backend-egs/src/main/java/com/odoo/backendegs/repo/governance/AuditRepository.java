package com.odoo.backendegs.repo.governance;


import com.odoo.backendegs.entity.governance.Audit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuditRepository extends JpaRepository<Audit, Long> {
}