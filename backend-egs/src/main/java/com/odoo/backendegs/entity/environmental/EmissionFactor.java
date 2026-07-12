package com.odoo.backendegs.entity.environmental;
import com.odoo.backendegs.entity.BaseSuperClass;
import com.odoo.backendegs.enums.ResourceCategory;
import com.odoo.backendegs.enums.UnitType;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "emission_factors")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmissionFactor extends BaseSuperClass {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Double factor;

    @Enumerated(EnumType.STRING)
    private UnitType unit;

    private String source;

    private LocalDate effectiveFrom;

    private LocalDate effectiveTo;

    @OneToOne
    @JoinColumn(name = "resource_id")
    private Resource resource;
}