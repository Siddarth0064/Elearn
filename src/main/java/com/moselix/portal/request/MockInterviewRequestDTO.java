package com.moselix.portal.request;

import lombok.Data;
import java.time.LocalDate;

@Data
public class MockInterviewRequestDTO {
    private Long studentId;
    private String interviewerName;
    private LocalDate interviewDate;
    private Integer score;
    private String feedback;
}
