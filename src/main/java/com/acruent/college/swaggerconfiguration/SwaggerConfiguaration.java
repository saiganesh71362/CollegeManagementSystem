package com.acruent.college.swaggerconfiguration;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfiguaration {
	
	@Bean
	public GroupedOpenApi controllerApi()
	{
	        return GroupedOpenApi.builder()
	                .group("StudentManagement-Api")
	                .packagesToScan("com.acruent.college.controller") // Specify the package to scan
	                .build();
	 }

}
