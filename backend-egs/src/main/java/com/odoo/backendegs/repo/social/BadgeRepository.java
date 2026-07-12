package com.odoo.backendegs.repo.social;

import com.odoo.backendegs.entity.social.badge.Badge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BadgeRepository extends JpaRepository<Badge, Long> {

    List<Badge> findByOrderByRequiredXpAsc();

}