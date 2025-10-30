package com.moselix.portal.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TopStudentDTO {
    private Long studentId;
    private String name;
    private Integer totalMarks;   // For top scorers
    private Double attendancePercent; // For top attendance
    
    public TopStudentDTO(Long studentId, String name, Long totalMarks) {
        this.studentId = studentId;
        this.name = name;
        this.totalMarks = totalMarks != null ? totalMarks.intValue() : 0;
    }

}

