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

import com.ofertas.entities.SupplierEntity;
import com.ofertas.services.SupplierService;

@RestController
@RequestMapping("/supplier")
public class SupplierController {

	@Autowired
	@Qualifier("supplierService")
	private SupplierService supplierService;
	
	@PostMapping("/create")
	public ResponseEntity<Object> create(@RequestBody SupplierEntity supplierEntity) {
		ResponseEntity<Object> responseEntity = supplierService.createSupplier(supplierEntity);
		
		if (responseEntity.hasBody()) {
			return new ResponseEntity<>(responseEntity.getBody(), HttpStatus.OK);
		}
		return responseEntity;
	}
	
	@PostMapping("/remove")
	public ResponseEntity<Object> remove(@RequestBody SupplierEntity supplierEntity) {
		ResponseEntity<Object> responseEntity = new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
		
		if (supplierService.removeSupplier(supplierEntity.getDocument())) {
			responseEntity = new ResponseEntity<>(HttpStatus.OK);
		}
		return responseEntity;
	}
	
	@PostMapping("/update")
	public ResponseEntity<Object> update(@RequestBody SupplierEntity supplierEntity) {
		ResponseEntity<Object> responseEntity = supplierService.updateSupplier(supplierEntity);
		
		if (responseEntity.hasBody()) {
			return new ResponseEntity<>(responseEntity.getBody(), HttpStatus.OK);
		}
		return responseEntity;
	}
	
	@GetMapping("/findAll")
	public ResponseEntity<Object> findAll() {
		ResponseEntity<Object> responseEntity = supplierService.findAllSuppliers();
		if (responseEntity.hasBody()) {
			return new ResponseEntity<>(responseEntity.getBody(), HttpStatus.OK);
		}
		return responseEntity;
	}
}
