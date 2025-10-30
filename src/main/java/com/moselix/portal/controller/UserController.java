package com.moselix.portal.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.moselix.portal.dto.LoginRequest;
import com.moselix.portal.dto.RegisterRequest;
import com.moselix.portal.dto.StudentProfileDTO;
import com.moselix.portal.modal.Student;
import com.moselix.portal.security.AuthService;
import com.moselix.portal.service.StudentService;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;

//import lombok.Value;



@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*") // Allow React Native calls
@RequiredArgsConstructor
public class UserController {

	 
	 private final StudentService studentService;
	 private final AuthService authService; // your login check
	 
//     @Autowired
//	    private OpenAIService openAIService;
//
//
//	    @PostMapping("/aichatebox")
//	    public ChatResponse chat(@RequestBody ChatRequest request) {
//	        String reply = openAIService.getChatResponse(request.getMessage());
//	        return new ChatResponse(reply);
//	    }
	 
	 

	    @Value("${huggingface.api.url}")
	    private String apiUrl;

	    @Value("${huggingface.api.key}")
	    private String apiKey;

	    @Value("${huggingface.model}")
	    private String modelName;
	    
	    @PostConstruct
	    public void checkEnv() {
	        System.out.println("🔹 apiUrl = " + apiUrl);
	        System.out.println("🔹 apiKey = " + apiKey);
	        System.out.println("🔹 modelName = " + modelName);
	    }

	    private final RestTemplate restTemplate = new RestTemplate();

	    @PostMapping("/aichatebox")
	    public ResponseEntity<Map<String, String>> chat(@RequestBody Map<String, String> body) {
	        String userMessage = body.get("message");

	        try {
	            // Prepare payload for Hugging Face OpenAI-compatible API
	            Map<String, Object> payload = new HashMap<>();
	            payload.put("model", modelName);
	            payload.put("messages", List.of(
	                    Map.of("role", "user", "content", userMessage)
	            ));

	            HttpHeaders headers = new HttpHeaders();
	            headers.setContentType(MediaType.APPLICATION_JSON);
	            headers.setBearerAuth(apiKey);

	            HttpEntity<Map<String, Object>> entity = new HttpEntity<>(payload, headers);

	            ResponseEntity<Map> response = restTemplate.exchange(
	                    apiUrl,
	                    HttpMethod.POST,
	                    entity,
	                    Map.class
	            );

	            // Parse the response from Hugging Face (OpenAI-like structure)
	            Map<String, Object> bodyMap = response.getBody();
	            String reply = "";

	            if (bodyMap != null && bodyMap.containsKey("choices")) {
	                List<Map<String, Object>> choices = (List<Map<String, Object>>) bodyMap.get("choices");
	                if (!choices.isEmpty()) {
	                    Map<String, Object> message = (Map<String, Object>) choices.get(0).get("message");
	                    reply = (String) message.get("content");
	                }
	            }

	            Map<String, String> result = new HashMap<>();
	            result.put("reply", reply != null ? reply : "No response from AI 🤖");

	            return ResponseEntity.ok(result);

	        } catch (Exception e) {
	            Map<String, String> error = new HashMap<>();
	            error.put("reply", "⚠️ Error: " + e.getMessage());
	            return ResponseEntity.status(500).body(error);
	        }
	    }
	 
	 
	 
	 
	 
	 
	 
	 
	 

//	    @PostMapping("/register")
//	    public String register(@RequestBody RegisterRequest request) {
//	        return userService.registerUser(request);
//	    }
//
//	    @PostMapping("/login")
//	    public  String login(@RequestBody LoginRequest request) {
////	        return userService.loginUser(request);
//	        return ResponseEntity.ok(Map.of("message", "Login successful!", "name", request.getEmail())); ResponseEntity<Map<String, String>>
//	    }
	   

	    @PostMapping("/login")
	    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
	        boolean authenticated = authService.validateUser(request.getEmail(), request.getPassword());

	        if (!authenticated) {
	            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
	                    .body(Map.of("status", "failed", "message", "Invalid credentials"));
	        }

	        StudentProfileDTO profile = studentService.getStudentProfile(request.getEmail());

	        return ResponseEntity.ok(Map.of(
	                "status", "success",
	                "user", profile
	        ));
	    }
	    
	    
	    
	    @PostMapping("/register")
	    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
	        try {
	            Student student = authService.registerStudent(request);
	            return ResponseEntity.ok(Map.of(
	                    "status", "success",
	                    "message", "Registration successful",
	                    "studentId", student.getStudentId()
	            ));
	        } catch (RuntimeException e) {
	            return ResponseEntity.badRequest().body(Map.of(
	                    "status", "failed",
	                    "message", e.getMessage()
	            ));
	        }
	    }
}

