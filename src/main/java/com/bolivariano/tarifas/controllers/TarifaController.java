package com.bolivariano.tarifas.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bolivariano.tarifas.modelos.Tarifa;
import com.bolivariano.tarifas.services.TarifaService;

@RestController
@RequestMapping("/tarifa")
public class TarifaController {
	
	@Autowired
	@Qualifier("tarifaService")
	private TarifaService tarifaService;
	
	
	@PostMapping("/crear")
	public ResponseEntity<Object> create(@RequestBody Tarifa tarifas) {
		ResponseEntity<Object> responseEntity  = tarifaService.crear(tarifas);
		if (responseEntity.hasBody()) {
			return new ResponseEntity<>(responseEntity.getBody(), HttpStatus.OK);
		}
		return responseEntity;
	}
	
	@PostMapping("/eliminar")
	public ResponseEntity<Object> eeliminar(@RequestBody Tarifa tarifas) {
		ResponseEntity<Object> responseEntity = new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
		
		if (tarifaService.eliminar(tarifas)) {
			responseEntity = new ResponseEntity<>(HttpStatus.OK);
		}
		return responseEntity;
	}
	
	@PostMapping("/actualizar")
	public ResponseEntity<Object> actualizar(@RequestBody Tarifa tarifa) {
		ResponseEntity<Object> responseEntity = tarifaService.actualizar(tarifa);
		
		if (responseEntity.hasBody()) {
			return new ResponseEntity<>(responseEntity.getBody(), HttpStatus.OK);
		}
		return responseEntity;
	}
	
	@GetMapping("/buscarTodos")
	public ResponseEntity<Object> buscarTodos() {
		ResponseEntity<Object> responseEntity = tarifaService.buscarTodos();

		if (responseEntity.hasBody()) {
			return new ResponseEntity<>(responseEntity.getBody(), HttpStatus.OK);
		}
		return responseEntity;
	}
}
