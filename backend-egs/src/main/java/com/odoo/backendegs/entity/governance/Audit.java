package com.odoo.backendegs.entity.governance;

import com.odoo.backendegs.entity.BaseSuperClass;
import com.odoo.backendegs.entity.department.Department;
import com.odoo.backendegs.enums.AuditStatus;
import com.odoo.backendegs.enums.AuditType;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "audits")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Audit extends BaseSuperClass {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(length = 1000)
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id")
    private Department department;

    @Enumerated(EnumType.STRING)
    private AuditType auditType;

    @Enumerated(EnumType.STRING)
    @Builder.Default
    private AuditStatus status = AuditStatus.PENDING;

    @Column(nullable = false)
    private LocalDate auditDate;

    @OneToMany(
            mappedBy = "audit",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @Builder.Default
    private List<ComplianceIssue> complianceIssues = new ArrayList<>();

}