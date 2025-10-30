package com.moselix.portal.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MockInterviewDTO {
    private String interviewerName;
    private String interviewDate;
    private Integer score;
    private String feedback;
}

