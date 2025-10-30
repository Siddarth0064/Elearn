package com.moselix.portal.service;


import org.springframework.stereotype.Service;

import com.moselix.portal.modal.Trainer;
import com.moselix.portal.repository.TrainerRepository;
import com.moselix.portal.request.TrainerRequestDTO;
import com.moselix.portal.response.TrainerResponseDTO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TrainerService {

    private final TrainerRepository trainerRepository;

    // Add new trainer
    public TrainerResponseDTO addTrainer(TrainerRequestDTO dto) {
        Trainer trainer = Trainer.builder()
                .name(dto.getName())
                .email(dto.getEmail())
                .phone(dto.getPhone())
                .expertise(dto.getExpertise())
                .build();

        Trainer savedTrainer = trainerRepository.save(trainer);

        return TrainerResponseDTO.builder()
                .trainerId(savedTrainer.getTrainerId())
                .name(savedTrainer.getName())
                .email(savedTrainer.getEmail())
                .phone(savedTrainer.getPhone())
                .expertise(savedTrainer.getExpertise())
                .build();
    }
}
