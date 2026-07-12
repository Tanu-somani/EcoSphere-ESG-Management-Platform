package com.odoo.backendegs.repo.environmental;

import com.odoo.backendegs.entity.environmental.EmissionFactor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmissionFactorRepo extends JpaRepository<EmissionFactor, Long> {
}
