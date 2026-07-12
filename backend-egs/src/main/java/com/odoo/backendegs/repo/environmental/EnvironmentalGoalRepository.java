package com.odoo.backendegs.repo.environmental;

import com.odoo.backendegs.entity.environmental.CarbonTransaction;
import com.odoo.backendegs.entity.environmental.EnvironmentalGoal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EnvironmentalGoalRepository extends JpaRepository<EnvironmentalGoal, Long> {

    List<EnvironmentalGoal> findByDepartmentId(Long departmentId);
}
