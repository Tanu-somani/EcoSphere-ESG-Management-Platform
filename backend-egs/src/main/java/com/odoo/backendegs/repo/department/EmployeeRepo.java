package com.odoo.backendegs.repo.department;

import com.odoo.backendegs.entity.department.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee,Long> {
}
