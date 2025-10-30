package com.moselix.portal.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.moselix.portal.modal.Attendance;
import com.moselix.portal.modal.Batch;
import com.moselix.portal.modal.Student;
import com.moselix.portal.service.AttendanceService;
import com.moselix.portal.service.BatchService;
import com.moselix.portal.service.StudentService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/attendance")
@RequiredArgsConstructor
public class AttendanceController {

    private final AttendanceService attendanceService;
    private final StudentService studentService;
    private final BatchService batchService;

    // Mark attendance for a student
    @PostMapping("/mark")
    public Attendance markAttendance(@RequestBody Map<String, String> payload) {
        Student student = studentService.getStudentById(Long.parseLong(payload.get("studentId")));
        Batch batch = batchService.getBatchById(Long.parseLong(payload.get("batchId")));
        LocalDate date = LocalDate.parse(payload.get("date"));
        Attendance.Status status = Attendance.Status.valueOf(payload.get("status"));

        return attendanceService.markAttendance(student, batch, date, status);
    }

    // Get attendance for a student in a batch
    @GetMapping("/student/{studentId}/batch/{batchId}")
    public List<Attendance> getAttendance(@PathVariable Long studentId, @PathVariable Long batchId) {
        Student student = studentService.getStudentById(studentId);
        Batch batch = batchService.getBatchById(batchId);
        return attendanceService.getAttendanceForStudent(student, batch);
    }

    // Get attendance percentage
    @GetMapping("/student/{studentId}/batch/{batchId}/percentage")
    public Map<String, Object> getAttendancePercentage(@PathVariable Long studentId, @PathVariable Long batchId) {
        Student student = studentService.getStudentById(studentId);
        Batch batch = batchService.getBatchById(batchId);
        double percentage = attendanceService.getAttendancePercentage(student, batch);

        return Map.of("studentId", studentId, "batchId", batchId, "attendancePercentage", percentage);
    }
}
