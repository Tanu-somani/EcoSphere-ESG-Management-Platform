package com.odoo.backendegs.repo.department;

import com.odoo.backendegs.entity.department.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepo extends JpaRepository<Department,Long> {

    
}
