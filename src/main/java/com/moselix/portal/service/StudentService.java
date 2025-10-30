package com.moselix.portal.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.moselix.portal.dto.AttendanceDTO;
import com.moselix.portal.dto.CourseDTO;
import com.moselix.portal.dto.ExamResultDTO;
import com.moselix.portal.dto.MockInterviewDTO;
import com.moselix.portal.dto.PlacementDTO;
import com.moselix.portal.dto.StudentProfileDTO;
import com.moselix.portal.dto.TopStudentDTO;
import com.moselix.portal.modal.Student;
import com.moselix.portal.repository.AttendanceRepository;
import com.moselix.portal.repository.ExamResultRepository;
import com.moselix.portal.repository.MockInterviewRepository;
import com.moselix.portal.repository.PlacementRepository;
import com.moselix.portal.repository.StudentCourseRepository;
import com.moselix.portal.repository.StudentRepository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;


import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;
    private final AttendanceRepository attendanceRepository;
    private final ExamResultRepository examResultRepository;
    private final MockInterviewRepository mockInterviewRepository;
    private final PlacementRepository placementRepository;
    private final StudentCourseRepository studentCourseRepository;
    
    
    // Fetch Student entity by ID
    public Student getStudentById(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found with ID: " + id));
    }
    

    public StudentProfileDTO getStudentProfile(String email) {
        Student student = studentRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        List<AttendanceDTO> attendance = attendanceRepository.findByStudent(student)
                .stream()
                .map(a -> new AttendanceDTO(a.getDate().toString(), a.getStatus().name()))
                .toList();

        List<ExamResultDTO> examResults = examResultRepository.findByStudent(student)
                .stream()
                .map(r -> new ExamResultDTO(
                        r.getExam().getExamName(),
                        r.getExam().getExamDate().toString(),
                        r.getExam().getTotalMarks(),
                        r.getMarksObtained(),
                        r.getRemarks()))
                .toList();

        List<MockInterviewDTO> interviews = mockInterviewRepository.findByStudent(student)
                .stream()
                .map(i -> new MockInterviewDTO(
                        i.getInterviewerName(),
                        i.getInterviewDate().toString(),
                        i.getScore(),
                        i.getFeedback()))
                .toList();

        List<PlacementDTO> placements = placementRepository.findByStudent(student)
                .stream()
                .map(p -> new PlacementDTO(
                        p.getCompanyName(),
                        p.getPosition(),
                        p.getPackageOffered().doubleValue(),
                        p.getPlacementDate() != null ? p.getPlacementDate().toString() : null,
                        p.getStatus().name()))
                .toList();

        List<CourseDTO> courses = studentCourseRepository.findByStudent(student)
                .stream()
                .map(sc -> new CourseDTO(
                        sc.getCourse().getCourseId(),
                        sc.getCourse().getCourseName(),
                        sc.getCourse().getDescription(),
                        sc.getBatch().getBatchName(),
                        sc.getBatch().getTrainer().getName()))
                .toList();

        // 1️⃣ Find the batch of the student (assuming one active batch)
        var studentCourse = studentCourseRepository.findByStudent(student)
                .stream()
                .filter(sc -> sc.getStatus().equals("Ongoing"))
                .findFirst()
                .orElse(null);

        List<TopStudentDTO> topScorers = List.of();
        List<TopStudentDTO> topAttendance = List.of();

        if (studentCourse != null) {
            Long batchId = studentCourse.getBatch().getBatchId();
            Long courseId = studentCourse.getCourse().getCourseId();

            topScorers = examResultRepository.findTopScorersInBatch(batchId, courseId, PageRequest.of(0,3));
            topAttendance = attendanceRepository.findTopAttendanceInBatch(batchId, PageRequest.of(0,3));
        }

        return StudentProfileDTO.builder()
                .studentId(student.getStudentId())
                .firstName(student.getFirstName())
                .lastName(student.getLastName())
                .email(student.getEmail())
                .phone(student.getPhone())
                .joinDate(student.getJoinDate())
                .status(student.getStatus().name())
                .courses(courses)
                .attendance(attendance)
                .examResults(examResults)
                .mockInterviews(interviews)
                .placements(placements)
                .topScorers(topScorers)
                .topAttendance(topAttendance)
                .build();
    }
    
    
    public StudentProfileDTO getStudentProfileById(Long id) {
        // ✅ 1. Fetch the student by ID safely
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found with ID: " + id));

        // ✅ 2. Attendance Details
        List<AttendanceDTO> attendance = attendanceRepository.findByStudent(student)
                .stream()
                .map(a -> new AttendanceDTO(
                        a.getDate().toString(),
                        a.getStatus().name()))
                .toList();

        // ✅ 3. Exam Results
        List<ExamResultDTO> examResults = examResultRepository.findByStudent(student)
                .stream()
                .map(r -> new ExamResultDTO(
                        r.getExam().getExamName(),
                        r.getExam().getExamDate().toString(),
                        r.getExam().getTotalMarks(),
                        r.getMarksObtained(),
                        r.getRemarks()))
                .toList();

        // ✅ 4. Mock Interviews
        List<MockInterviewDTO> interviews = mockInterviewRepository.findByStudent(student)
                .stream()
                .map(i -> new MockInterviewDTO(
                        i.getInterviewerName(),
                        i.getInterviewDate().toString(),
                        i.getScore(),
                        i.getFeedback()))
                .toList();

        // ✅ 5. Placements
        List<PlacementDTO> placements = placementRepository.findByStudent(student)
                .stream()
                .map(p -> new PlacementDTO(
                        p.getCompanyName(),
                        p.getPosition(),
                        p.getPackageOffered().doubleValue(),
                        p.getPlacementDate() != null ? p.getPlacementDate().toString() : null,
                        p.getStatus().name()))
                .toList();

        // ✅ 6. Courses & Batch Info
        List<CourseDTO> courses = studentCourseRepository.findByStudent(student)
                .stream()
                .map(sc -> new CourseDTO(
                        sc.getCourse().getCourseId(),
                        sc.getCourse().getCourseName(),
                        sc.getCourse().getDescription(),
                        sc.getBatch().getBatchName(),
                        sc.getBatch().getTrainer().getName()))
                .toList();

        // ✅ 7. Find active batch & course for analytics
        var studentCourse = studentCourseRepository.findByStudent(student)
                .stream()
                .filter(sc -> sc.getStatus().equals("Ongoing"))
                .findFirst()
                .orElse(null);

        List<TopStudentDTO> topScorers = List.of();
        List<TopStudentDTO> topAttendance = List.of();

        if (studentCourse != null) {
            Long batchId = studentCourse.getBatch().getBatchId();
            Long courseId = studentCourse.getCourse().getCourseId();

            topScorers = examResultRepository.findTopScorersInBatch(batchId, courseId, PageRequest.of(0, 3));
            topAttendance = attendanceRepository.findTopAttendanceInBatch(batchId, PageRequest.of(0, 3));
        }

        // ✅ 8. Build and return the DTO
        return StudentProfileDTO.builder()
                .studentId(student.getStudentId())
                .firstName(student.getFirstName())
                .lastName(student.getLastName())
                .email(student.getEmail())
                .phone(student.getPhone())
                .joinDate(student.getJoinDate())
                .status(student.getStatus().name())
                .courses(courses)
                .attendance(attendance)
                .examResults(examResults)
                .mockInterviews(interviews)
                .placements(placements)
                .topScorers(topScorers)
                .topAttendance(topAttendance)
                .build();
    }

}
