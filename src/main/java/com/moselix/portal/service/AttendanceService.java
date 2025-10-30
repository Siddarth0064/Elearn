package com.moselix.portal.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.moselix.portal.modal.Attendance;
import com.moselix.portal.modal.Batch;
import com.moselix.portal.modal.Student;
import com.moselix.portal.repository.AttendanceRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AttendanceService {

    private final AttendanceRepository attendanceRepository;

    // 1️⃣ Mark attendance for a student
    public Attendance markAttendance(Student student, Batch batch, LocalDate date, Attendance.Status status) {
        // Check if attendance already exists
        List<Attendance> existing = attendanceRepository.findByStudentAndBatchAndDate(student, batch, date);
        if (!existing.isEmpty()) {
            // Update status if already exists
            Attendance attendance = existing.get(0);
            attendance.setStatus(status);
            return attendanceRepository.save(attendance);
        }

        // Else create new attendance
        Attendance attendance = Attendance.builder()
                .student(student)
                .batch(batch)
                .date(date)
                .status(status)
                .build();

        return attendanceRepository.save(attendance);
    }

    // 2️⃣ Get attendance for a student in a batch
    public List<Attendance> getAttendanceForStudent(Student student, Batch batch) {
        return attendanceRepository.findByStudentAndBatch(student, batch);
    }

    // 3️⃣ Get attendance for a batch on a specific date
    public List<Attendance> getAttendanceForBatchByDate(Batch batch, LocalDate date) {
        return attendanceRepository.findByBatchAndDate(batch, date);
    }

    // 4️⃣ Get attendance percentage for a student in a batch
    public double getAttendancePercentage(Student student, Batch batch) {
        List<Attendance> records = attendanceRepository.findByStudentAndBatch(student, batch);
        if (records.isEmpty()) return 0.0;

        long presentCount = records.stream()
                .filter(a -> a.getStatus() == Attendance.Status.Present)
                .count();

        return (presentCount * 100.0 / records.size());
    }
    



}
