package com.odoo.backendegs.repo.governance;

import com.odoo.backendegs.entity.governance.Policy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PolicyRepository extends JpaRepository<Policy, Long> {
}