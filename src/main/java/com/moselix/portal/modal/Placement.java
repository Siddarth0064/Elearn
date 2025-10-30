package com.moselix.portal.modal;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "placements")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Placement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "placement_id")
    private Long placementId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    @Column(name = "company_name", length = 150, nullable = false)
    private String companyName;

    @Column(name = "position", length = 100)
    private String position;

    @Column(name = "package", precision = 10, scale = 2)
    private BigDecimal packageOffered;

    @Column(name = "placement_date")
    private LocalDate placementDate;

    @Enumerated(EnumType.STRING)
    @Column(length = 20, nullable = false)
    private Status status = Status.Pending;

    public enum Status {
        Placed,
        Pending,
        Rejected
    }
}
