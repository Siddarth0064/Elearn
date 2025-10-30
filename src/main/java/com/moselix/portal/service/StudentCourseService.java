package com.moselix.portal.service;

import org.springframework.stereotype.Service;

import com.moselix.portal.modal.Batch;
import com.moselix.portal.modal.Course;
import com.moselix.portal.modal.Student;
import com.moselix.portal.modal.StudentCourse;
import com.moselix.portal.repository.BatchRepository;
import com.moselix.portal.repository.CourseRepository;
import com.moselix.portal.repository.StudentCourseRepository;
import com.moselix.portal.repository.StudentRepository;
import com.moselix.portal.request.StudentCourseRequestDTO;
import com.moselix.portal.response.StudentCourseResponseDTO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StudentCourseService {

    private final StudentCourseRepository studentCourseRepository;
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;
    private final BatchRepository batchRepository;

    // Add a new StudentCourse
    public StudentCourseResponseDTO addStudentCourse(StudentCourseRequestDTO dto) {

        Student student = studentRepository.findById(dto.getStudentId())
                .orElseThrow(() -> new RuntimeException("Student not found with ID: " + dto.getStudentId()));

        Course course = courseRepository.findById(dto.getCourseId())
                .orElseThrow(() -> new RuntimeException("Course not found with ID: " + dto.getCourseId()));

        Batch batch = batchRepository.findById(dto.getBatchId())
                .orElseThrow(() -> new RuntimeException("Batch not found with ID: " + dto.getBatchId()));

        StudentCourse studentCourse = StudentCourse.builder()
                .student(student)
                .course(course)
                .batch(batch)
                .joinDate(dto.getJoinDate())
                .completionDate(dto.getCompletionDate())
                .status(StudentCourse.Status.valueOf(dto.getStatus()))
                .build();

        StudentCourse saved = studentCourseRepository.save(studentCourse);

        return StudentCourseResponseDTO.builder()
                .id(saved.getId())
                .studentName(saved.getStudent().getFirstName() + " " + saved.getStudent().getLastName())
                .courseName(saved.getCourse().getCourseName())
                .batchName(saved.getBatch().getBatchName())
                .joinDate(saved.getJoinDate())
                .completionDate(saved.getCompletionDate())
                .status(saved.getStatus().name())
                .build();
    }
}

