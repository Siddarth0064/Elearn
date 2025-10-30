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
public class StudentCourseResponseDTO {
    private Long id;
    private String studentName;
    private String courseName;
    private String batchName;
    private LocalDate joinDate;
    private LocalDate completionDate;
    private String status;
}
