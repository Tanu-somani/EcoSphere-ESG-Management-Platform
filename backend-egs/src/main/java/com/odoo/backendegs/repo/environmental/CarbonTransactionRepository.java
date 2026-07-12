package com.odoo.backendegs.repo.environmental;

import com.odoo.backendegs.entity.environmental.CarbonTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CarbonTransactionRepository extends JpaRepository<CarbonTransaction, Long> {

    @Query("""
SELECT COALESCE(SUM(c.carbonGenerated),0)
FROM CarbonTransaction c
WHERE c.department.id = :departmentId
""")
    Double getTotalCarbonEmission(@Param("departmentId") Long departmentId);

    Optional<CarbonTransaction> findByDepartmentId(Long departmentId);
}
