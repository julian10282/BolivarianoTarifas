package com.ofertas.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ofertas.entities.ProductEntity;
import com.ofertas.repository.ProductRepository;

@Service("productService")
public class ProductService {
	
	@Autowired
	@Qualifier("productRepository")
	private ProductRepository productRepository;
	
	public ResponseEntity<Object> createProduct (ProductEntity productEntity) {
		try {
			productEntity.setCode(0);
			ProductEntity productEntity2 = productRepository.save(productEntity);

			return new ResponseEntity<>(productEntity2, HttpStatus.OK);
			
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	public boolean removeProduct (int code) {
		try {
			ProductEntity productEntity = productRepository.findByCode(code);
			if (productEntity != null) {
				productRepository.delete(productEntity);
				return true;
			}
			return false;
		} catch (Exception e) {
			return false;
		}
	}
	
	public ResponseEntity<Object> updateProduct (ProductEntity productEntity) {
		try {
			ProductEntity productEntity2 = productRepository.findByCode(productEntity.getCode());
			
			if (productEntity2 != null) {
				ProductEntity productEntity3 = productRepository.save(productEntity);
				return new ResponseEntity<>(productEntity3, HttpStatus.OK);
			}
			return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	public ResponseEntity<Object> findAllProducts () {
		try {
			List<ProductEntity> productEntities = productRepository.findAll();
			return new ResponseEntity<>(productEntities, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	public ResponseEntity<Object> findByCatalogCode(int id) {
		try {
			List<ProductEntity> productEntities = productRepository.findByCatalogCode(id);
			return new ResponseEntity<>(productEntities, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	public ResponseEntity<Object> findByCode(int code) {
		try {
			ProductEntity productEntity = productRepository.findByCode(code);
			return new ResponseEntity<>(productEntity, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
}
