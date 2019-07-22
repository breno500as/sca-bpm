package com.puc.sca.bpm.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;

@RestController
@RequestMapping("eureka")
public class EurekaDiscoveryClientController {
	
	// Exemplo de descoberta dinâmica de microserviço para obter a url
	@Autowired
	private EurekaClient discoveryClient;

	@GetMapping("topograficos")
	public List<Object> getProcessosTopograficos() throws ClientProtocolException, IOException {
		final HttpClient httpClient = HttpClients.createDefault();
		
		final InstanceInfo instance = discoveryClient.getNextServerFromEureka("sca-crud", false);
	 	
		final HttpGet httpget = new HttpGet(instance.getHomePageUrl() + "sca-crud/tipos-marca-modelo");

		final HttpResponse response = httpClient.execute(httpget);

		final HttpEntity entity = response.getEntity();

		final String content = EntityUtils.toString(entity);

		final List<Object> objetos = new ArrayList<Object>();
		objetos.add(content);

		return objetos;
	}

}
