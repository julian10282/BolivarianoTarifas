package com.ofertas.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ofertas.entities.RequestEntity;
import com.ofertas.services.RequestService;

@RestController
@RequestMapping("/request")
public class RequestController {

	@Autowired
	@Qualifier("requestService")
	private RequestService requestService;
	
	@PostMapping("/create")
	public ResponseEntity<Object> create(@RequestBody RequestEntity requestEntity) {
		ResponseEntity<Object> responseEntity = requestService.createRequest(requestEntity);
		if (responseEntity.hasBody()) {

			requestService.sendRequestToSupplier(responseEntity.getBody());
			
			return new ResponseEntity<>(responseEntity.getBody(), HttpStatus.OK);
		}
		return responseEntity;
	}
	
	@PostMapping("/remove")
	public ResponseEntity<Object> remove(@RequestBody RequestEntity requestEntity) {
		ResponseEntity<Object> responseEntity = new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
		
		if (requestService.removeRequest(requestEntity.getId())) {
			responseEntity = new ResponseEntity<>(HttpStatus.OK);
		}
		return responseEntity;
	}
	
	@PostMapping("/update")
	public ResponseEntity<Object> update(@RequestBody RequestEntity requestEntity) {
		ResponseEntity<Object> responseEntity = requestService.updateRequest(requestEntity);
		
		if (responseEntity.hasBody()) {
			return new ResponseEntity<>(responseEntity.getBody(), HttpStatus.OK);
		}
		return responseEntity;
	}
	
	@GetMapping("/findAll")
	public ResponseEntity<Object> findAll() {
		ResponseEntity<Object> responseEntity = requestService.findAllRequest();
		
		if (responseEntity.hasBody()) {
			return new ResponseEntity<>(responseEntity.getBody(), HttpStatus.OK);
		}
		return responseEntity;
	}
	
	@GetMapping("findByClientDocument")
	public ResponseEntity<Object> findByClientDocument(@RequestParam String document){
		ResponseEntity<Object> responseEntity = requestService.findByClientDocument(document);
		
		if (responseEntity.hasBody()) {
			return new ResponseEntity<>(responseEntity.getBody(), HttpStatus.OK);
		}
		return responseEntity;
	}
}
