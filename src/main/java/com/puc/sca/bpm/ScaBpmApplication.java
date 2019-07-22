package com.puc.sca.bpm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;



@SpringBootApplication
@EnableDiscoveryClient
public class ScaBpmApplication {

	public static void main(String[] args) {
		SpringApplication.run(ScaBpmApplication.class, args);
	}

}
