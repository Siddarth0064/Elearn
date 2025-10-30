package com.moselix.portal.request;


import lombok.Data;

import java.time.LocalDate;

@Data
public class ExamRequestDTO {
    private String examName;
    private Long courseId;
    private LocalDate examDate;
    private Integer totalMarks;
}
