package com.moselix.portal.request;


import lombok.Data;

import java.time.LocalDate;

@Data
public class BatchRequestDTO {
    private String batchName;
    private Long courseId;
    private Long trainerId;
    private LocalDate startDate;
    private LocalDate endDate;
}
