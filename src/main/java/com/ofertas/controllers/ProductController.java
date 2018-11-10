package com.ofertas.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ofertas.entities.ProductEntity;
import com.ofertas.services.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {
	
	@Autowired
	@Qualifier("productService")
	private ProductService productService;

	@PostMapping("/create")
	public ResponseEntity<Object> create(@RequestBody ProductEntity productEntity) {
		ResponseEntity<Object> responseEntity = productService.createProduct(productEntity);
		if (responseEntity.hasBody()) {
			return new ResponseEntity<>(responseEntity.getBody(), HttpStatus.OK);
		}
		return responseEntity;
	}
	
	@PostMapping("/remove")
	public ResponseEntity<Object> remove(@RequestBody ProductEntity productEntity) {
		ResponseEntity<Object> responseEntity = new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
		
		if (productService.removeProduct(productEntity.getCode())) {
			responseEntity = new ResponseEntity<>(HttpStatus.OK);
		}
		return responseEntity;
	}
	
	@PostMapping("/update")
	public ResponseEntity<Object> update(@RequestBody ProductEntity productEntity) {
		ResponseEntity<Object> responseEntity = productService.updateProduct(productEntity);
		
		if (responseEntity.hasBody()) {
			return new ResponseEntity<>(responseEntity.getBody(), HttpStatus.OK);
		}
		return responseEntity;
	}
	
	@GetMapping("/findAll")
	public ResponseEntity<Object> findAll() {
		ResponseEntity<Object> responseEntity = productService.findAllProducts();

		if (responseEntity.hasBody()) {
			return new ResponseEntity<>(responseEntity.getBody(), HttpStatus.OK);
		}
		return responseEntity;
	}
	
	@GetMapping("/findByCatalogCode")
	public ResponseEntity<Object> findByCatalogDocument(@RequestParam int id) {
		ResponseEntity<Object> responseEntity = productService.findByCatalogCode(id);
		
		if (responseEntity.hasBody()) {
			return new ResponseEntity<>(responseEntity.getBody(), HttpStatus.OK);
		}
		return responseEntity;
	}
	
	@GetMapping("/findByCode/{code}")
	public ResponseEntity<Object> findByCode(@PathVariable int code) {
		ResponseEntity<Object> responseEntity = productService.findByCode(code);
		
		if (responseEntity.hasBody()) {
			return new ResponseEntity<>(responseEntity.getBody(), HttpStatus.OK);
		}
		return responseEntity;
	}
}
