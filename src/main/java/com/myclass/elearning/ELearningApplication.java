package com.myclass.elearning;

import com.myclass.elearning.entity.Role;
import com.myclass.elearning.repo.RoleRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class ELearningApplication {

    public static void main(String[] args) {
        SpringApplication.run(ELearningApplication.class, args);
    }

    @Bean
    public WebMvcConfigurer webMvcConfigurer(){
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/api/**")
                        .allowedOrigins("*")
//                        .allowCredentials(false)
                        .allowedMethods("GET", "POST", "PUT", "DELETE")
                        .maxAge(4800);
            }
        };
    }
}
