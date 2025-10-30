package com.moselix.portal.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.moselix.portal.request.PlacementRequestDTO;
import com.moselix.portal.response.PlacementResponseDTO;
import com.moselix.portal.service.PlacementService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/placements")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class PlacementController {

    private final PlacementService placementService;

    // ✅ Add placement
    @PostMapping("/add")
    public ResponseEntity<PlacementResponseDTO> addPlacement(@RequestBody PlacementRequestDTO request) {
        PlacementResponseDTO response = placementService.addPlacement(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
