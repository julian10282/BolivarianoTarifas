package com.ofertas.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ofertas.entities.ClientEntity;
import com.ofertas.services.ClientService;

@RestController
@RequestMapping("/client")
public class ClientController {
	
	@Autowired
	@Qualifier("clientService")
	private ClientService clientService;
	
	
	@PostMapping("/create")
	public ResponseEntity<Object> create(@RequestBody ClientEntity clientEntity) {
		ResponseEntity<Object> responseEntity  = clientService.createClient(clientEntity);
		if (responseEntity.hasBody()) {
			return new ResponseEntity<>(responseEntity.getBody(), HttpStatus.OK);
		}
		return responseEntity;
	}
	
	@PostMapping("/remove")
	public ResponseEntity<Object> remove(@RequestBody ClientEntity clientEntity) {
		ResponseEntity<Object> responseEntity = new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
		
		if (clientService.removeClient(clientEntity.getDocument())) {
			responseEntity = new ResponseEntity<>(HttpStatus.OK);
		}
		return responseEntity;
	}
	
	@PostMapping("/update")
	public ResponseEntity<Object> update(@RequestBody ClientEntity clientEntity) {
		ResponseEntity<Object> responseEntity = clientService.updateClient(clientEntity);
		
		if (responseEntity.hasBody()) {
			return new ResponseEntity<>(responseEntity.getBody(), HttpStatus.OK);
		}
		return responseEntity;
	}
	
	@GetMapping("/findAll")
	public ResponseEntity<Object> findAll() {
		ResponseEntity<Object> responseEntity = clientService.findAllClients();

		if (responseEntity.hasBody()) {
			return new ResponseEntity<>(responseEntity.getBody(), HttpStatus.OK);
		}
		return responseEntity;
	}
}
