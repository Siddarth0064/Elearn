package com.moselix.portal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.moselix.portal.modal.Batch;
import com.moselix.portal.modal.Course;

public interface BatchRepository extends JpaRepository<Batch, Long>{
	List<Batch> findByCourse_CourseId(Long id);
	
	

}
