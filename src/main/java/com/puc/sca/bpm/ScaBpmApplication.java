package com.puc.sca.bpm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


/**
 * Subir no tomcat 9 os wars: flowable-idm.war, flowable-modeler.war
 * URL do flowabble http://localhost:8161/modeler
 * usuário: admin 
 * senha: test
 * @author Breno
 *
 */

@SpringBootApplication
@EnableDiscoveryClient
public class ScaBpmApplication {

	public static void main(String[] args) {
		SpringApplication.run(ScaBpmApplication.class, args);
	}

}
