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
public class MockInterviewResponseDTO {
    private Long mockId;
    private String studentName;
    private String interviewerName;
    private LocalDate interviewDate;
    private Integer score;
    private String feedback;
}
