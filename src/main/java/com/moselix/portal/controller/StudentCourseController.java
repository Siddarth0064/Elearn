package com.moselix.portal.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.moselix.portal.request.StudentCourseRequestDTO;
import com.moselix.portal.response.StudentCourseResponseDTO;
import com.moselix.portal.service.StudentCourseService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/student-courses")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class StudentCourseController {

    private final StudentCourseService studentCourseService;

    // API to add a new StudentCourse
    @PostMapping("/add")
    public ResponseEntity<StudentCourseResponseDTO> addStudentCourse(@RequestBody StudentCourseRequestDTO request) {
        StudentCourseResponseDTO response = studentCourseService.addStudentCourse(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
