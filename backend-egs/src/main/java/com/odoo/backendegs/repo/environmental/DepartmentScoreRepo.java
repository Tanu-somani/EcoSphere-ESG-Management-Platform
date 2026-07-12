package com.odoo.backendegs.repo.environmental;

import com.odoo.backendegs.entity.environmental.DepartmentScore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentScoreRepo extends JpaRepository<DepartmentScore, Long> {
}
