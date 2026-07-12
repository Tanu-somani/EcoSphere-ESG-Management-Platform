package com.odoo.backendegs.repo.social;

import com.odoo.backendegs.entity.social.XPTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface XPTransactionRepository extends JpaRepository<XPTransaction, Long> {

    List<XPTransaction> findByDepartmentId(Long departmentId);

    List<XPTransaction> findByEmployeeName(String employeeName);

    @Query("""
            SELECT COALESCE(SUM(x.xpEarned),0)
            FROM XPTransaction x
            WHERE x.department.id = :departmentId
            """)
    Integer getTotalDepartmentXP(@Param("departmentId") Long departmentId);

    @Query("""
            SELECT COALESCE(SUM(x.xpEarned),0)
            FROM XPTransaction x
            WHERE x.employeeName = :employeeName
            """)
    Integer getEmployeeXP(@Param("employeeName") String employeeName);

}