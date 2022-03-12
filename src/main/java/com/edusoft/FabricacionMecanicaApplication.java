package com.edusoft;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.edusoft.models.service.IUploadFileService;


@SpringBootApplication
public class FabricacionMecanicaApplication implements CommandLineRunner, WebMvcConfigurer{

	@Autowired
	IUploadFileService uploadFileService;
	
	
	public static void main(String[] args) {
		SpringApplication.run(FabricacionMecanicaApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		//uploadFileService.deleteAll();
		uploadFileService.init();
	}
	
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/grafica_talleres").setViewName("grafica_talleres");
        registry.addViewController("/grafica_proyectos").setViewName("grafica_proyectos");
    }
	
	 
	    

}
