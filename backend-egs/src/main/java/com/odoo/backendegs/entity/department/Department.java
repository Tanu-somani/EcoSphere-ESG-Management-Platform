package com.odoo.backendegs.entity.department;

import com.odoo.backendegs.entity.esg.DepartmentScore;
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
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    private String description;

    private String managerName;

    @OneToMany(mappedBy = "department")
    @Builder.Default
    private List<Employee> employees = new ArrayList<>();

    @OneToOne(mappedBy = "department", cascade = CascadeType.ALL)
    private DepartmentScore departmentScore;

}
