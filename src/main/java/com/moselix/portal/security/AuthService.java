package com.moselix.portal.security;

import java.time.LocalDate;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.moselix.portal.dto.RegisterRequest;
import com.moselix.portal.modal.Student;
import com.moselix.portal.repository.StudentRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final StudentRepository studentRepository;
    private final PasswordEncoder passwordEncoder;


    /**
     * Validate user credentials
     * @param email    user's email
     * @param password plain text password from request
     * @return true if valid credentials
     */
    public boolean validateUser(String email, String password) {
        return studentRepository.findByEmail(email)
                .map(student -> passwordEncoder.matches(password, student.getPassword()))
                .orElse(false);
    }

    /**
     * Get full student after successful login
     */
    public Student getStudentByEmail(String email) {
        return studentRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Student not found"));
    }
    
    
    
    
    public Student registerStudent(RegisterRequest request) {
        // Check if email already exists
        if (studentRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("Email already registered");
        }

        // Build Student entity
        Student student = Student.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .phone(request.getPhone())
                .gender(request.getGender())
                .dob(request.getDob())
                .address(request.getAddress())
                .joinDate(LocalDate.now()) // default join date
                .password(passwordEncoder.encode(request.getPassword())) // hash password
                .status(Student.Status.Active) // default status
                .build();

        return studentRepository.save(student);
    }
}
