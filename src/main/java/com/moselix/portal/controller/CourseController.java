package com.moselix.portal.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.moselix.portal.request.CourseRequestDTO;
import com.moselix.portal.response.CourseResponseDTO;
import com.moselix.portal.service.CourseService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/courses")
@RequiredArgsConstructor
@CrossOrigin(origins = "*") // allow frontend calls
public class CourseController {

    private final CourseService courseService;

    // Endpoint to add a new course
    @PostMapping("/add")
    public ResponseEntity<CourseResponseDTO> addCourse(@RequestBody CourseRequestDTO courseRequest) {
        CourseResponseDTO response = courseService.addCourse(courseRequest);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
