package com.odoo.backendegs.entity.social;

import com.odoo.backendegs.entity.BaseSuperClass;
import com.odoo.backendegs.enums.social.CSRActivityStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "csr_activities")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CSRActivity extends BaseSuperClass {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(length = 1000)
    private String description;

    @Column(nullable = false)
    private Integer xpReward;

    @Column(nullable = false)
    private LocalDate startDate;

    @Column(nullable = false)
    private LocalDate endDate;

    @Enumerated(EnumType.STRING)
    @Builder.Default
    private CSRActivityStatus status = CSRActivityStatus.UPCOMING;

    @OneToMany(mappedBy = "csrActivity",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    @Builder.Default
    private List<Participation> participations = new ArrayList<>();

}
