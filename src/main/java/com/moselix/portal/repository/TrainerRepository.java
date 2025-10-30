package com.moselix.portal.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.moselix.portal.modal.Trainer;

public interface TrainerRepository extends JpaRepository<Trainer, Long>{
	 Optional<Trainer> findById(Long Id);

}
