package com.acruent.college;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class CollegeMangementApplication {

	public static void main(String[] args) {
		SpringApplication.run(CollegeMangementApplication.class, args);
	}

}
