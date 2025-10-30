package com.moselix.portal.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.moselix.portal.request.BatchRequestDTO;
import com.moselix.portal.response.BatchResponseDTO;
import com.moselix.portal.service.BatchService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/batches")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class BatchController {

    private final BatchService batchService;

    // API to add a new batch
    @PostMapping("/add")
    public ResponseEntity<BatchResponseDTO> addBatch(@RequestBody BatchRequestDTO batchRequest) {
        BatchResponseDTO response = batchService.addBatch(batchRequest);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
