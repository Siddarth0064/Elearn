package com.moselix.portal.service;


import org.springframework.stereotype.Service;

import com.moselix.portal.modal.Course;
import com.moselix.portal.repository.CourseRepository;
import com.moselix.portal.request.CourseRequestDTO;
import com.moselix.portal.response.CourseResponseDTO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CourseService {

    private final CourseRepository courseRepository;

    // Add a new course
    public CourseResponseDTO addCourse(CourseRequestDTO dto) {

        Course course = Course.builder()
                .courseName(dto.getCourseName())
                .description(dto.getDescription())
                .durationWeeks(dto.getDurationWeeks())
                .startDate(dto.getStartDate())
                .endDate(dto.getEndDate())
                .build();

        Course savedCourse = courseRepository.save(course);

        // Map entity to response DTO
        return CourseResponseDTO.builder()
                .courseId(savedCourse.getCourseId())
                .courseName(savedCourse.getCourseName())
                .description(savedCourse.getDescription())
                .durationWeeks(savedCourse.getDurationWeeks())
                .startDate(savedCourse.getStartDate())
                .endDate(savedCourse.getEndDate())
                .build();
    }
}
