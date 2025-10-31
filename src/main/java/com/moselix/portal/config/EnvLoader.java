package com.moselix.portal.config;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.context.annotation.Configuration;

import jakarta.annotation.PostConstruct;

@Configuration
public class EnvLoader {

//    @PostConstruct
//    public void loadEnv() {
//        Dotenv dotenv = Dotenv.load();
//        dotenv.entries().forEach(entry -> {
//            // Add env vars as System properties if not already present
//            if (System.getenv(entry.getKey()) == null) {
//                System.setProperty(entry.getKey(), entry.getValue());
//            }
//        });
//        System.out.println("✅ .env variables loaded successfully into system properties.");
//    }

    @PostConstruct
    public void loadEnv() {
        // Only load .env locally, not on Render
        if (System.getenv("RENDER") != null) {
            System.out.println("✅ Running on Render – skipping .env load.");
            return;
        }

        try {
            Dotenv dotenv = Dotenv.load();
            dotenv.entries().forEach(entry -> {
                if (System.getenv(entry.getKey()) == null) {
                    System.setProperty(entry.getKey(), entry.getValue());
                }
            });
            System.out.println("✅ .env variables loaded successfully into system properties.");
        } catch (Exception e) {
            System.out.println("⚠️ No .env file found. Skipping dotenv load.");
        }
    }
}
