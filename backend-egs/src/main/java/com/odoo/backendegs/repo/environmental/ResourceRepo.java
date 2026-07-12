package com.odoo.backendegs.repo.environmental;

import com.odoo.backendegs.entity.environmental.DepartmentScore;
import com.odoo.backendegs.entity.environmental.Resource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResourceRepo extends JpaRepository<Resource, Long> {
}
