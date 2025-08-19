//package com.task.TaskManagement.Config;
//
//
////package com.task.TaskManagement.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.CorsRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//@Configuration
//public class WebConfig {
//
//  @Bean
//  public WebMvcConfigurer corsConfigurer() {
//      return new WebMvcConfigurer() {
//          @Override
//          public void addCorsMappings(CorsRegistry registry) {
//              registry.addMapping("/**") // apply to all paths
//                      .allowedOrigins("http://localhost:4200") // allow your frontend origin
//                      .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS","PATCH") // allowed HTTP methods
//                      .allowedHeaders("*")
//                      .allowCredentials(true);
//          }
//      };
//  }
//}