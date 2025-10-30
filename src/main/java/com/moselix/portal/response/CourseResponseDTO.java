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
public class CourseResponseDTO {
    private Long courseId;
    private String courseName;
    private String description;
    private Integer durationWeeks;
    private LocalDate startDate;
    private LocalDate endDate;
}
