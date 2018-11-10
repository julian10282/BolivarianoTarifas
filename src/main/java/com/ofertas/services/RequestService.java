package com.ofertas.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMethod;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mysql.fabric.xmlrpc.base.Data;
import com.ofertas.entities.ItemEntity;
import com.ofertas.entities.RequestEntity;
import com.ofertas.integration.RestProxy;
import com.ofertas.repository.ItemRepository;
import com.ofertas.repository.RequestRepository;

@Service("requestService")
public class RequestService {

	@Autowired
	@Qualifier("requestRepository")
	private RequestRepository requestRepository;
	
	@Autowired
	@Qualifier("itemRepository")
	private ItemRepository itemRepository;
	
	@Autowired
	@Qualifier("restIntegrationProxy")
	private RestProxy restProxy;
	
	private static final Log LOG = LogFactory.getLog(RequestEntity.class);
	
	private static final String URL = "http://localhost:8080/proveedor/cotizacion/recibir";
	
	public ResponseEntity<Object> createRequest (RequestEntity requestEntity) {
		try {
			requestEntity.setId(0);
			requestEntity.setStartDate(new Date());
			requestEntity.setFinalDate(new Date());
			RequestEntity requestEntity2 = requestRepository.save(requestEntity);
			
			if (requestEntity.getItemEntities() != null) {
				
				List<ItemEntity> itemEntities = new ArrayList<>();
				
				for (ItemEntity itemEntity: requestEntity.getItemEntities()) {
					itemEntity.setRequestId(requestEntity2.getId());
					
					ItemEntity itemEntity2 = itemRepository.save(itemEntity);
					
					if (itemEntity2 != null) {
						itemEntities.add(itemEntity2);
						LOG.info("Item saved Item="+itemEntity2.toString());
					}
				}
				requestEntity2.setItemEntities(itemEntities);
				LOG.info("Request saved Resquets="+requestEntity2.toString());
			}
			
			
			return new ResponseEntity<>(requestEntity2, HttpStatus.OK);
			
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	public boolean removeRequest (int id) {
		try {
			RequestEntity requestEntity = requestRepository.findById(id);
			if (requestEntity != null) {
				requestRepository.delete(requestEntity);
				return true;
			}
			return false;
		} catch (Exception e) {
			return false;
		}
	}
	
	public ResponseEntity<Object> updateRequest (RequestEntity requestEntity) {
		try {
			RequestEntity requestEntity2 = requestRepository.findById(requestEntity.getId());
			
			if (requestEntity2 != null) {
				RequestEntity requestEntity3= requestRepository.save(requestEntity);
				return new ResponseEntity<>(requestEntity3, HttpStatus.OK);
			}
			return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	public ResponseEntity<Object> findAllRequest () {
		try {
			List<RequestEntity> requestEntities = requestRepository.findAll();
			return new ResponseEntity<>(requestEntities, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

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
			LOG.info("Error enviando solicitud al proveedor!! ERROR="+ e.getMessage());
		}
		
	}
	
	public ResponseEntity<Object> findByClientDocument(String document){
		try {
			List<RequestEntity> requestEntities = requestRepository.findByClientDocument(document);
			return new ResponseEntity<>(requestEntities, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
}
