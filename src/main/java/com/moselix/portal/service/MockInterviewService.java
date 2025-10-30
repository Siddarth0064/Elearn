package com.moselix.portal.service;


import org.springframework.stereotype.Service;

import com.moselix.portal.modal.MockInterview;
import com.moselix.portal.modal.Student;
import com.moselix.portal.repository.MockInterviewRepository;
import com.moselix.portal.repository.StudentRepository;
import com.moselix.portal.request.MockInterviewRequestDTO;
import com.moselix.portal.response.MockInterviewResponseDTO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MockInterviewService {

    private final MockInterviewRepository mockInterviewRepository;
    private final StudentRepository studentRepository;

    // Add new mock interview record
    public MockInterviewResponseDTO addMockInterview(MockInterviewRequestDTO dto) {

        Student student = studentRepository.findById(dto.getStudentId())
                .orElseThrow(() -> new RuntimeException("Student not found with ID: " + dto.getStudentId()));

        MockInterview mockInterview = MockInterview.builder()
                .student(student)
                .interviewerName(dto.getInterviewerName())
                .interviewDate(dto.getInterviewDate())
                .score(dto.getScore())
                .feedback(dto.getFeedback())
                .build();

        MockInterview saved = mockInterviewRepository.save(mockInterview);

        return MockInterviewResponseDTO.builder()
                .mockId(saved.getMockId())
                .studentName(saved.getStudent().getFirstName() + " " + saved.getStudent().getLastName())
                .interviewerName(saved.getInterviewerName())
                .interviewDate(saved.getInterviewDate())
                .score(saved.getScore())
                .feedback(saved.getFeedback())
                .build();
    }
}
