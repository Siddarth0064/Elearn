//package com.moselix.portal.service;
//
//import java.util.Optional;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//
//import com.moselix.portal.dto.LoginRequest;
//import com.moselix.portal.dto.RegisterRequest;
//import com.moselix.portal.modal.User;
//import com.moselix.portal.repository.UserRepository;
//
//
//
//
//@Service
//public class UserService {
//
//    @Autowired
//    private UserRepository repo;
//
//    @Autowired
//    private PasswordEncoder passwordEncoder;
//
//    public String registerUser(RegisterRequest request) {
//        if (repo.findByEmail(request.getEmail()).isPresent()) {
//            return "Email already registered!";
//        }
//
//        User user = new User(
//        		request.getName(),
//                request.getEmail(),
//                passwordEncoder.encode(request.getPassword())
//        );
//
//        repo.save(user);
//        return "success";
//    }
//
//    public String loginUser(LoginRequest request) {
//        Optional<User> user = repo.findByEmail(request.getEmail());
//        if (user.isPresent() && passwordEncoder.matches(request.getPassword(), user.get().getPassword())) {
//            return "success";
//        } else {
//            return "Invalid credentials!";
//        }
//    }
//}
//
