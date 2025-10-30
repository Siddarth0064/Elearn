package com.moselix.portal.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BatchResponseDTO {
    private Long batchId;
    private String batchName;
    private String courseName;
    private String trainerName;
    private LocalDate startDate;
    private LocalDate endDate;
}
