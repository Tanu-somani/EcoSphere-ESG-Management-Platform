package com.odoo.backendegs.repo.social;

import com.odoo.backendegs.entity.social.CSRActivity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CSRActivityRepository extends JpaRepository<CSRActivity, Long> {
}
