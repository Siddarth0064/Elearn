package com.moselix.portal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.moselix.portal.modal.Student;
import com.moselix.portal.modal.StudentCourse;

public interface StudentCourseRepository extends JpaRepository<StudentCourse, Long>{
	List<StudentCourse> findByStudent(Student std);

}
