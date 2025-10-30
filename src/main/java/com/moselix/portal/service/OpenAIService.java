//package com.moselix.portal.service;
//
//
//
//import com.fasterxml.jackson.databind.JsonNode;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Service;
//import org.springframework.web.client.RestTemplate;
//import org.springframework.http.*;
//
//import java.util.*;
//
//@Service
//public class OpenAIService {
//
//    @Value("${openai.api.key}")
//    private String apiKey;
//
//    @Value("${openai.api.url}")
//    private String apiUrl;
//
//    private final RestTemplate restTemplate = new RestTemplate();
//    private final ObjectMapper mapper = new ObjectMapper();
//
//    public String getChatResponse(String userMessage) {
//        try {
//            // Prepare request body
//            Map<String, Object> body = new HashMap<>();
//            body.put("model", "gpt-4o-mini"); // You can use gpt-3.5-turbo if needed
//
//            List<Map<String, String>> messages = new ArrayList<>();
//            messages.add(Map.of("role", "system", "content",
//                    "You are 'VLSI Assistant' — a helpful and friendly AI who helps students learn VLSI design concepts."));
//            messages.add(Map.of("role", "user", "content", userMessage));
//
//            body.put("messages", messages);
//
//            // Headers
//            HttpHeaders headers = new HttpHeaders();
//            headers.setContentType(MediaType.APPLICATION_JSON);
//            headers.setBearerAuth(apiKey);
//
//            HttpEntity<Map<String, Object>> request = new HttpEntity<>(body, headers);
//
//            // Send request
//            ResponseEntity<String> response = restTemplate.postForEntity(apiUrl, request, String.class);
//
//            // Parse JSON
//            JsonNode root = mapper.readTree(response.getBody());
//            String reply = root.path("choices").get(0).path("message").path("content").asText();
//
//            return reply;
//        } catch (Exception e) {
//            e.printStackTrace();
//            return "⚠️ Sorry, I couldn't process your request right now.";
//        }
//    }
//}
