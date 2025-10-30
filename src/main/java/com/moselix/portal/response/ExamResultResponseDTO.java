package com.moselix.portal.response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ExamResultResponseDTO {
    private Long resultId;
    private String studentName;
    private String examName;
    private Integer marksObtained;
    private String remarks;
}
