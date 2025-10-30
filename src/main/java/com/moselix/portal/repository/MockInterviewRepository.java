package com.moselix.portal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.moselix.portal.modal.MockInterview;
import com.moselix.portal.modal.Student;

public interface MockInterviewRepository extends JpaRepository<MockInterview, Long> {
	 List<MockInterview> findByStudent(Student std);
}
