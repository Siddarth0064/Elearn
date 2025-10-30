package com.moselix.portal.request;


import lombok.Data;

@Data
public class ExamResultRequestDTO {
    private Long examId;
    private Long studentId;
    private Integer marksObtained;
    private String remarks;
}
