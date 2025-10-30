package com.moselix.portal.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.moselix.portal.request.TrainerRequestDTO;
import com.moselix.portal.response.TrainerResponseDTO;
import com.moselix.portal.service.TrainerService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/trainers")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class TrainerController {

    private final TrainerService trainerService;

    // API to add a new trainer
    @PostMapping("/add")
    public ResponseEntity<TrainerResponseDTO> addTrainer(@RequestBody TrainerRequestDTO trainerRequest) {
        TrainerResponseDTO response = trainerService.addTrainer(trainerRequest);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
