package com.moselix.portal.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.moselix.portal.modal.Student;

public interface StudentRepository extends JpaRepository<Student, Long>{
	 Optional<Student> findByEmail(String email);
}
