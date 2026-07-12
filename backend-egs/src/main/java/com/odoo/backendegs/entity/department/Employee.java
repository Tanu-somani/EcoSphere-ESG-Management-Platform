package com.odoo.backendegs.entity.department;

import com.odoo.backendegs.entity.BaseSuperClass;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Employee extends BaseSuperClass {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String firstName;

    private String lastName;

    private String designation;

    @Email
    @Column(unique = true, nullable = false)
    private String email;

    @Builder.Default
    private Integer totalXp = 0;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id")
    private Department department;


}
