package com.moselix.portal.controller;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.moselix.portal.dto.StudentProfileDTO;
import com.moselix.portal.service.StudentService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/student")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class StudentController {

    private final StudentService studentService;

    // ✅ Correct REST endpoint
    @GetMapping("/{id}")
    public ResponseEntity<StudentProfileDTO> getStudentProfile(@PathVariable Long id) {
        StudentProfileDTO response = studentService.getStudentProfileById(id);
        return ResponseEntity.ok(response);
    }

}



