package com.moselix.portal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.moselix.portal.modal.Placement;
import com.moselix.portal.modal.Student;

public interface PlacementRepository extends JpaRepository<Placement, Long>{
	List<Placement> findByStudent(Student std);
}
