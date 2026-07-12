package com.odoo.backendegs.entity.environmental;
import com.odoo.backendegs.entity.BaseSuperClass;
import com.odoo.backendegs.entity.department.Department;
import com.odoo.backendegs.enums.GoalStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "environmental_goals")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EnvironmentalGoal extends BaseSuperClass {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(length = 1000)
    private String description;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "department_id", nullable = false)
    private Department department;

    @Column(nullable = false)
    private Double targetCarbon;

    @Builder.Default
    private Double currentCarbon = 0.0;

    @Column(nullable = false)
    private LocalDate startDate;

    @Column(nullable = false)
    private LocalDate targetDate;

    @Enumerated(EnumType.STRING)
    @Builder.Default
    private GoalStatus status = GoalStatus.ACTIVE;
}