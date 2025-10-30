package com.moselix.portal.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.moselix.portal.request.MockInterviewRequestDTO;
import com.moselix.portal.response.MockInterviewResponseDTO;
import com.moselix.portal.service.MockInterviewService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/mock-interviews")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class MockInterviewController {

    private final MockInterviewService mockInterviewService;

    // ✅ Add new mock interview
    @PostMapping("/add")
    public ResponseEntity<MockInterviewResponseDTO> addMockInterview(@RequestBody MockInterviewRequestDTO request) {
        MockInterviewResponseDTO response = mockInterviewService.addMockInterview(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
