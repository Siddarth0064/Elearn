package com.moselix.portal.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PlacementDTO {
    private String companyName;
    private String position;
    private Double packageOffered;
    private String placementDate;
    private String status;
}
