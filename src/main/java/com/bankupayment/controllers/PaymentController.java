package com.bankupayment.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bankupayment.entities.PaymentEntity;
import com.bankupayment.services.PaymentService;

@RestController
@RequestMapping("/payment")
public class PaymentController {
	
	@Autowired
	@Qualifier("paymentService")
	private PaymentService paymentService;
	
	
	@PostMapping("/create")
	public ResponseEntity<Object> create(@RequestBody PaymentEntity paymentEntity) {
		ResponseEntity<Object> responseEntity  = paymentService.createPay(paymentEntity);
		if (responseEntity.hasBody()) {
			return new ResponseEntity<>(responseEntity.getBody(), HttpStatus.OK);
		}
		return responseEntity;
	}
	
	@GetMapping("/findByAccount/{account}")
	public ResponseEntity<Object> findByAccount(@PathVariable String account) {
		ResponseEntity<Object> responseEntity = paymentService.findByAccount(account);

		if (responseEntity.hasBody()) {
			return new ResponseEntity<>(responseEntity.getBody(), HttpStatus.OK);
		}
		return responseEntity;
	}
	

	@PostMapping("/update")
	public ResponseEntity<Object> update(@RequestBody PaymentEntity clientEntity) {
		ResponseEntity<Object> responseEntity = paymentService.updatePayment(clientEntity);
		
		if (responseEntity.hasBody()) {
			return new ResponseEntity<>(responseEntity.getBody(), HttpStatus.OK);
		}
		return responseEntity;
	}
	
	@PostMapping("/pay")
	public ResponseEntity<Object> pay(@RequestBody PaymentEntity paymentEntity) {
		ResponseEntity<Object> responseEntity = paymentService.pay(paymentEntity);
		
		if (responseEntity.hasBody()) {
			return new ResponseEntity<>(responseEntity.getBody(), HttpStatus.OK);
		}
		return responseEntity;
	}
	
}
