package com.moselix.portal.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.moselix.portal.request.ExamResultRequestDTO;
import com.moselix.portal.response.ExamResultResponseDTO;
import com.moselix.portal.service.ExamResultService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/exam-results")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ExamResultController {

    private final ExamResultService examResultService;

    // API to add a new exam result
    @PostMapping("/add")
    public ResponseEntity<ExamResultResponseDTO> addExamResult(@RequestBody ExamResultRequestDTO request) {
        ExamResultResponseDTO response = examResultService.addExamResult(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
