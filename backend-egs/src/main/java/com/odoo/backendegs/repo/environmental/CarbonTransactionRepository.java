package com.odoo.backendegs.repo.environmental;

import com.odoo.backendegs.entity.environmental.CarbonTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarbonTransactionRepository extends JpaRepository<CarbonTransaction, Long> {
}
