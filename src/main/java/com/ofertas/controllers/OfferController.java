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

import com.ofertas.entities.OfferEntity;
import com.ofertas.services.OfferService;

@RestController
@RequestMapping("/offer")
public class OfferController {

	@Autowired
	@Qualifier("offerService")
	private OfferService offerService;
	
	@PostMapping("/create")
	public ResponseEntity<Object> create(@RequestBody OfferEntity offerEntity) {
		ResponseEntity<Object> responseEntity = offerService.createOffer(offerEntity);
		if (responseEntity.hasBody()) {
			return new ResponseEntity<>(responseEntity.getBody(), HttpStatus.OK);
		}
		return responseEntity;
	}
	
	@PostMapping("/remove")
	public ResponseEntity<Object> remove(@RequestBody OfferEntity offerEntity) {
		ResponseEntity<Object> responseEntity = new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
		
		if (offerService.removeOffer(offerEntity.getId())) {
			responseEntity = new ResponseEntity<>(HttpStatus.OK);
		}
		return responseEntity;
	}
	
	@PostMapping("/update")
	public ResponseEntity<Object> update(@RequestBody OfferEntity offerEntity) {
		ResponseEntity<Object> responseEntity = offerService.updateOffer(offerEntity);
		
		if (responseEntity.hasBody()) {
			return new ResponseEntity<>(responseEntity.getBody(), HttpStatus.OK);
		}
		return responseEntity;
	}
	
	@GetMapping("/findAll")
	public ResponseEntity<Object> findAll() {
		ResponseEntity<Object> responseEntity = offerService.findAllOffers();

		if (responseEntity.hasBody()) {
			return new ResponseEntity<>(responseEntity.getBody(), HttpStatus.OK);
		}
		return responseEntity;
	}
	
	@GetMapping("findByRequestId")
	public ResponseEntity<Object> findOfferByRequestId(@RequestParam int id){
		ResponseEntity<Object> responseEntity = offerService.findOfferByRequestId(id);
		
		if (responseEntity.hasBody()) {
			return new ResponseEntity<>(responseEntity.getBody(), HttpStatus.OK);
		}
		return responseEntity;
	}
}
