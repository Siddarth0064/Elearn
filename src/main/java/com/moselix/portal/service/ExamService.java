package com.moselix.portal.service;

import org.springframework.stereotype.Service;

import com.moselix.portal.modal.Course;
import com.moselix.portal.modal.Exam;
import com.moselix.portal.repository.CourseRepository;
import com.moselix.portal.repository.ExamRepository;
import com.moselix.portal.request.ExamRequestDTO;
import com.moselix.portal.response.ExamResponseDTO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ExamService {

    private final ExamRepository examRepository;
    private final CourseRepository courseRepository;

    // Add a new exam
    public ExamResponseDTO addExam(ExamRequestDTO dto) {
        Course course = courseRepository.findById(dto.getCourseId())
                .orElseThrow(() -> new RuntimeException("Course not found with ID: " + dto.getCourseId()));

        Exam exam = Exam.builder()
                .examName(dto.getExamName())
                .course(course)
                .examDate(dto.getExamDate())
                .totalMarks(dto.getTotalMarks())
                .build();

        Exam savedExam = examRepository.save(exam);

        return ExamResponseDTO.builder()
                .examId(savedExam.getExamId())
                .examName(savedExam.getExamName())
                .courseName(savedExam.getCourse().getCourseName())
                .examDate(savedExam.getExamDate())
                .totalMarks(savedExam.getTotalMarks())
                .build();
    }
}
