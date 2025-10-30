package com.moselix.portal.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExamResultDTO {
    private String examName;
    private String examDate;
    private Integer totalMarks;
    private Integer marksObtained;
    private String remarks;
}
