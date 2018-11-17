package com.bolivariano.tarifas.services;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bolivariano.tarifas.integration.RestProxy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Service("requestService")
public class RequestService {

	@Autowired
	@Qualifier("restIntegrationProxy")
	private RestProxy restProxy;
	
	private static final Log LOG = LogFactory.getLog(RequestEntity.class);
	
	private static final String URL = "http://localhost:8080/proveedor/cotizacion/recibir";
	

	public void sendRequestToSupplier(Object body) {
		try {
			
			RequestEntity requestEntity = (RequestEntity) body;
			
			Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
			String json = gson.toJson(requestEntity);
			
			Map<String, String> headers = new HashMap<>();
			headers.put("Content-Type", "application/json");
			
			ResponseEntity<Object> response = restProxy.sendRequest(RequestMethod.POST, URL, null, null, headers, json);
			
			LOG.info("Fin de envio de la oferta ='" + response.getBody().toString()+"'");
			
		} catch (Exception e) {
		}
		
	}
	
}
