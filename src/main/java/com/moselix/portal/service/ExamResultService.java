package com.moselix.portal.service;

import org.springframework.stereotype.Service;

import com.moselix.portal.modal.Exam;
import com.moselix.portal.modal.ExamResult;
import com.moselix.portal.modal.Student;
import com.moselix.portal.repository.ExamRepository;
import com.moselix.portal.repository.ExamResultRepository;
import com.moselix.portal.repository.StudentRepository;
import com.moselix.portal.request.ExamResultRequestDTO;
import com.moselix.portal.response.ExamResultResponseDTO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ExamResultService {

    private final ExamResultRepository examResultRepository;
    private final ExamRepository examRepository;
    private final StudentRepository studentRepository;

    // Add a new exam result
    public ExamResultResponseDTO addExamResult(ExamResultRequestDTO dto) {

        Exam exam = examRepository.findById(dto.getExamId())
                .orElseThrow(() -> new RuntimeException("Exam not found with ID: " + dto.getExamId()));

        Student student = studentRepository.findById(dto.getStudentId())
                .orElseThrow(() -> new RuntimeException("Student not found with ID: " + dto.getStudentId()));

        ExamResult result = ExamResult.builder()
                .exam(exam)
                .student(student)
                .marksObtained(dto.getMarksObtained())
                .remarks(dto.getRemarks())
                .build();

        ExamResult savedResult = examResultRepository.save(result);

        return ExamResultResponseDTO.builder()
                .resultId(savedResult.getResultId())
                .studentName(savedResult.getStudent().getFirstName() + " " + savedResult.getStudent().getLastName())
                .examName(savedResult.getExam().getExamName())
                .marksObtained(savedResult.getMarksObtained())
                .remarks(savedResult.getRemarks())
                .build();
    }
}
