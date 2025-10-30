package com.moselix.portal.request;


import lombok.Data;

import java.time.LocalDate;

@Data
public class StudentCourseRequestDTO {
    private Long studentId;
    private Long courseId;
    private Long batchId;
    private LocalDate joinDate;
    private LocalDate completionDate;
    private String status; // "Ongoing", "Completed", "Dropped"
}
