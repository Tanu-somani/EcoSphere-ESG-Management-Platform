package com.odoo.backendegs.repo.social;

import com.odoo.backendegs.entity.social.Participation;
import com.odoo.backendegs.enums.social.ParticipationStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParticipationRepository extends JpaRepository<Participation, Long> {

    List<Participation> findByDepartmentId(Long departmentId);

    List<Participation> findByStatus(ParticipationStatus status);

}