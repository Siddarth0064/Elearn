package com.moselix.portal.response;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PlacementResponseDTO {
    private Long placementId;
    private String studentName;
    private String companyName;
    private String position;
    private BigDecimal packageOffered;
    private LocalDate placementDate;
    private String status;
}
