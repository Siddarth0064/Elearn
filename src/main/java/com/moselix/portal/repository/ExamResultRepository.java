package com.moselix.portal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.moselix.portal.dto.TopStudentDTO;
import com.moselix.portal.modal.ExamResult;
import com.moselix.portal.modal.Student;

import org.springframework.data.domain.Pageable;

public interface ExamResultRepository extends JpaRepository<ExamResult, Long> {
	List<ExamResult> findByStudent(Student std);
	
	
	@Query("""
		       SELECT new com.moselix.portal.dto.TopStudentDTO(
		           r.student.studentId, 
		           CONCAT(r.student.firstName, ' ', r.student.lastName), 
		           SUM(r.marksObtained)
		       )
		       FROM ExamResult r
		       JOIN r.exam e
		       JOIN r.student s
		       WHERE e.course.courseId = :courseId AND r.student IN (
		           SELECT sc.student FROM StudentCourse sc WHERE sc.batch.batchId = :batchId
		       )
		       GROUP BY r.student.studentId, r.student.firstName, r.student.lastName
		       ORDER BY SUM(r.marksObtained) DESC
		       """)
		List<TopStudentDTO> findTopScorersInBatch(@Param("batchId") Long batchId, @Param("courseId") Long courseId, Pageable pageable);

}
