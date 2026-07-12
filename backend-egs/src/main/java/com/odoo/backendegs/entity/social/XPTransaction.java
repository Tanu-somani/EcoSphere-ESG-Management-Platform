package com.odoo.backendegs.entity.social;

import com.odoo.backendegs.entity.BaseSuperClass;
import com.odoo.backendegs.entity.department.Department;
import com.odoo.backendegs.enums.social.XPReason;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "xp_transactions")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class XPTransaction extends BaseSuperClass {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String employeeName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id")
    private Department department;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "participation_id")
    private Participation participation;

    @Column(nullable = false)
    private Integer xpEarned;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private XPReason reason;

    @Column(length = 500)
    private String remarks;

    @Column(nullable = false)
    private LocalDateTime awardedAt;

}