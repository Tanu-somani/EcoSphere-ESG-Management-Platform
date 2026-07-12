package com.odoo.backendegs.repo.environmental;

import com.odoo.backendegs.entity.esg.DepartmentScore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DepartmentScoreRepo extends JpaRepository<DepartmentScore, Long> {
    Optional<DepartmentScore> findByDepartmentId(Long departmentId);
}
