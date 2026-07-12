package com.odoo.backendegs.entity.governance;

import com.odoo.backendegs.entity.BaseSuperClass;
import com.odoo.backendegs.entity.department.Department;
import com.odoo.backendegs.enums.ComplianceStatus;
import com.odoo.backendegs.enums.Severity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "compliance_issues")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ComplianceIssue extends BaseSuperClass {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "audit_id")
    private Audit audit;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id")
    private Department department;

    @Column(nullable = false)
    private String title;

    @Column(length = 1000)
    private String description;

    @Enumerated(EnumType.STRING)
    private Severity severity;

    @Enumerated(EnumType.STRING)
    @Builder.Default
    private ComplianceStatus status = ComplianceStatus.OPEN;

    private LocalDateTime resolvedAt;

}