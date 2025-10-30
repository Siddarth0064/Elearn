package com.moselix.portal.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.moselix.portal.request.ExamRequestDTO;
import com.moselix.portal.response.ExamResponseDTO;
import com.moselix.portal.service.ExamService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/exams")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ExamController {

    private final ExamService examService;

    // API to add a new exam
    @PostMapping("/add")
    public ResponseEntity<ExamResponseDTO> addExam(@RequestBody ExamRequestDTO examRequest) {
        ExamResponseDTO response = examService.addExam(examRequest);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
