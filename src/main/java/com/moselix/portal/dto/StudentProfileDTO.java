package com.moselix.portal.dto;

import java.time.LocalDate;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudentProfileDTO {

    private Long studentId;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private LocalDate joinDate;
    private String status;

    private List<CourseDTO> courses;
    private List<AttendanceDTO> attendance;
    private List<ExamResultDTO> examResults;
    private List<MockInterviewDTO> mockInterviews;
    private List<PlacementDTO> placements;
    private List<TopStudentDTO> topScorers;
    private List<TopStudentDTO> topAttendance;
    
}

