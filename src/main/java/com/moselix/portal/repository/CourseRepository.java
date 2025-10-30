package com.moselix.portal.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.moselix.portal.modal.Course;

public interface CourseRepository extends JpaRepository<Course, Long> {
	Optional<Course> findById(Long id);

}
