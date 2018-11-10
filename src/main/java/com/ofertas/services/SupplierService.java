package com.ofertas.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ofertas.entities.SupplierEntity;
import com.ofertas.repository.SupplierRepository;

@Service("supplierService")
public class SupplierService {

	@Autowired
	@Qualifier("supplierRepository")
	private SupplierRepository supplierRepository;
	
	public ResponseEntity<Object> createSupplier (SupplierEntity supplierEntity) {
		try {
			supplierEntity.setDocument(0);
			SupplierEntity supplierEntity2 = supplierRepository.save(supplierEntity);

			return new ResponseEntity<>(supplierEntity2, HttpStatus.OK);
			
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	public boolean removeSupplier (int document) {
		try {
			SupplierEntity productEntity = supplierRepository.findByDocument(document);
			if (productEntity != null) {
				supplierRepository.delete(productEntity);
				return true;
			}
			return false;
		} catch (Exception e) {
			return false;
		}
	}
	
	public ResponseEntity<Object> updateSupplier (SupplierEntity supplierEntity) {
		try {
			SupplierEntity supplierEntity2= supplierRepository.findByDocument(supplierEntity.getDocument());
			
			if (supplierEntity2 != null) {
				SupplierEntity supplierEntity3 = supplierRepository.save(supplierEntity);
				return new ResponseEntity<>(supplierEntity3, HttpStatus.OK);
			}
			return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	public ResponseEntity<Object> findAllSuppliers () {
		try {
			List<SupplierEntity> supplierEntities = supplierRepository.findAll();
			return new ResponseEntity<>(supplierEntities, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
