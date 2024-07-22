package com.acruent.college.swaggerconfiguration;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.acruent.college.appconstants.AppConstants;

@Configuration
public class SwaggerConfiguaration {
	
	@Bean
	public GroupedOpenApi controllerApi()
	{
	        return GroupedOpenApi.builder()
	                .group(AppConstants.STUDENT_MANAGEMENT)
	                .packagesToScan(AppConstants.CONTROLLER_URL) // Specify the package to scan
	                .build();
	 }

}
