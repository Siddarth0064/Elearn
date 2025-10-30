//package com.moselix.portal.config;
//
//import io.github.cdimascio.dotenv.Dotenv;
//import org.springframework.context.ApplicationContextInitializer;
//import org.springframework.context.ConfigurableApplicationContext;
//import org.springframework.core.env.ConfigurableEnvironment;
//import org.springframework.core.env.MapPropertySource;
//
//import java.util.HashMap;
//import java.util.Map;
//
//public class DotenvInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
//
//    @Override
//    public void initialize(ConfigurableApplicationContext applicationContext) {
//        Dotenv dotenv = Dotenv.load();
//        Map<String, Object> map = new HashMap<>();
//
//        dotenv.entries().forEach(entry -> map.put(entry.getKey(), entry.getValue()));
//
//        ConfigurableEnvironment env = applicationContext.getEnvironment();
//        env.getPropertySources().addFirst(new MapPropertySource("dotenv", map));
//    }
//}
