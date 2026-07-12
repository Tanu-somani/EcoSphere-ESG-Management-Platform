package com.odoo.backendegs.entity.environmental;

import com.odoo.backendegs.enums.ResourceCategory;
import com.odoo.backendegs.enums.UnitType;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Resource {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Enumerated(EnumType.STRING)
    private ResourceCategory category;

    @Enumerated(EnumType.STRING)
    private UnitType unit;

    private String description;

    @OneToMany(mappedBy = "resource")
    @Builder.Default
    private List<EmissionFactor> emissionFactors = new ArrayList<>();
}
