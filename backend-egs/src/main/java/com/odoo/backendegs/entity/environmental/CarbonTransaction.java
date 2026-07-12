package com.odoo.backendegs.entity.environmental;
import com.odoo.backendegs.entity.BaseSuperClass;
import com.odoo.backendegs.entity.department.Department;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;


@Getter
@Setter
@Table(name = "CarbonTransaction")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CarbonTransaction extends BaseSuperClass {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "department_id", nullable = false)
    private Department department;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "resource_id", nullable = false)
    private Resource resource;

    @Column(nullable = false)
    private Double quantity;

    @Column(nullable = false)
    private Double emissionFactorUsed;

    @Column(nullable = false)
    private Double carbonGenerated;

    @Column(nullable = false)
    private LocalDate transactionDate;

    @Column(length = 500)
    private String notes;
}