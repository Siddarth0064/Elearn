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
public class ExamResponseDTO {
    private Long examId;
    private String examName;
    private String courseName;
    private LocalDate examDate;
    private Integer totalMarks;
}
