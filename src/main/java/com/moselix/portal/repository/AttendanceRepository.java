package com.moselix.portal.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.moselix.portal.dto.TopStudentDTO;
import com.moselix.portal.modal.Attendance;
import com.moselix.portal.modal.Batch;
import com.moselix.portal.modal.Student;

public interface AttendanceRepository extends JpaRepository<Attendance, Long> {
//	List<AttendanceDTO> findByStudent(Student std);
	 List<Attendance> findByStudent(Student student);
	 
	  // Find attendance for a student in a batch
	    List<Attendance> findByStudentAndBatch(Student student, Batch batch);

	    // Find attendance for a student on a specific date
	    List<Attendance> findByStudentAndBatchAndDate(Student student, Batch batch, LocalDate date);

	    // Find attendance by batch and date (for marking bulk attendance)
	    List<Attendance> findByBatchAndDate(Batch batch, LocalDate date);
	 
	 
	 @Query("""
		       SELECT new com.moselix.portal.dto.TopStudentDTO(
		           a.student.studentId,
		           CONCAT(a.student.firstName, ' ', a.student.lastName),
		           null,
		           (COUNT(CASE WHEN a.status = 'Present' THEN 1 END) * 100.0 / COUNT(a)) 
		       )
		       FROM Attendance a
		       WHERE a.batch.batchId = :batchId
		       GROUP BY a.student.studentId, a.student.firstName, a.student.lastName
		       ORDER BY (COUNT(CASE WHEN a.status = 'Present' THEN 1 END) * 100.0 / COUNT(a)) DESC
		       """)
		List<TopStudentDTO> findTopAttendanceInBatch(@Param("batchId") Long batchId, Pageable pageable);


}
