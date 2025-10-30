package com.moselix.portal.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class CourseRequestDTO {
    private String courseName;
    private String description;
    private Integer durationWeeks;
    private LocalDate startDate;
    private LocalDate endDate;
}

