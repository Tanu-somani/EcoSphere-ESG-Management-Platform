package com.odoo.backendegs.entity.esg;

import com.odoo.backendegs.entity.department.Department;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DepartmentScore {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Builder.Default
    private Double environmentScore = 0.0;

    @Builder.Default
    private Double socialScore = 0.0;

    @Builder.Default
    private Double governanceScore = 0.0;

    @Builder.Default
    private Double overallScore = 0.0;

    private LocalDateTime lastCalculated;

    @OneToOne
    @JoinColumn(name = "department_id", unique = true)
    private Department department;

}
