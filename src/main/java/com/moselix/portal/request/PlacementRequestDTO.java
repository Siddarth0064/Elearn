package com.moselix.portal.request;


import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.Data;

@Data
public class PlacementRequestDTO {
    private Long studentId;
    private String companyName;
    private String position;
    private BigDecimal packageOffered;
    private LocalDate placementDate;
    private String status; // Placed, Pending, Rejected
}
