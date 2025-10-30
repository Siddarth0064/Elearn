//package com.moselix.portal.config;
//
//import java.util.Properties;
//
//import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import io.github.cdimascio.dotenv.Dotenv;
//
//@Configuration
//public class EnvConfig {
//
////    @Bean
////    public StandardEnvironment environment() {
////        Dotenv dotenv = Dotenv.load();
////        Map<String, Object> envMap = new HashMap<>();
////        dotenv.entries().forEach(entry -> envMap.put(entry.getKey(), entry.getValue()));
////
////        StandardEnvironment environment = new StandardEnvironment();
////        MutablePropertySources propertySources = environment.getPropertySources();
////        propertySources.addFirst(new MapPropertySource("dotenv", envMap));
////
////        return environment;
////    }
//    @Bean
//    public static PropertyPlaceholderConfigurer propertyPlaceholderConfigurer() {
//        Dotenv dotenv = Dotenv.load();
//        Properties props = new Properties();
//
//        dotenv.entries().forEach(entry -> props.setProperty(entry.getKey(), entry.getValue()));
//
//        PropertyPlaceholderConfigurer configurer = new PropertyPlaceholderConfigurer();
//        configurer.setProperties(props);
//        return configurer;
//    }
//}
//
