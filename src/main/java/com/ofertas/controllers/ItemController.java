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

import com.ofertas.entities.ItemEntity;
import com.ofertas.services.ItemService;

@RestController
@RequestMapping("/item")
public class ItemController {
	
	@Autowired
	@Qualifier("itemService")
	private ItemService itemService;
	
	@PostMapping("/create")
	public ResponseEntity<Object> create(@RequestBody ItemEntity itemEntity) {
		ResponseEntity<Object> responseEntity = itemService.createItem(itemEntity);
		if (responseEntity.hasBody()) {
			return new ResponseEntity<>(responseEntity.getBody(), HttpStatus.OK);
		}
		return responseEntity;
	}
	
	@PostMapping("/remove")
	public ResponseEntity<Object> remove(@RequestBody ItemEntity itemEntity) {
		ResponseEntity<Object> responseEntity = new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
		
		if (itemService.removeItem(itemEntity.getId())) {
			responseEntity = new ResponseEntity<>(HttpStatus.OK);
		}
		return responseEntity;
	}
	
	@PostMapping("/update")
	public ResponseEntity<Object> update(@RequestBody ItemEntity itemEntity) {
		ResponseEntity<Object> responseEntity = itemService.updateItem(itemEntity);
		
		if (responseEntity.hasBody()) {
			return new ResponseEntity<>(responseEntity.getBody(), HttpStatus.OK);
		}
		return responseEntity;
	}
	
	@GetMapping("/findAll")
	public ResponseEntity<Object> findAll() {
		ResponseEntity<Object> responseEntity = itemService.findAllItems();
		
		if (responseEntity.hasBody()) {
			return new ResponseEntity<>(responseEntity.getBody(), HttpStatus.OK);
		}
		return responseEntity;
	}
	
	@GetMapping("/findByRequestId")
	public ResponseEntity<Object> findByRequestId(@RequestParam int id) {
		ResponseEntity<Object> responseEntity = itemService.findByRequestId(id);
		
		if (responseEntity.hasBody()) {
			return new ResponseEntity<>(responseEntity.getBody(), HttpStatus.OK);
		}
		return responseEntity;
	}

}
