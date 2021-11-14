package com.edusoft;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
 
@SpringBootApplication
public class SpringBootHighchartsExampleApplicationProyectos implements WebMvcConfigurer {
 
    public static void main(String[] args) {
        SpringApplication.run(SpringBootHighchartsExampleApplicationProyectos.class, args);
    }
 
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/grafica_proyectos").setViewName("grafica_proyectos");
    }
    
}
