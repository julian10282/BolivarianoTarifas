package com.ofertas.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ofertas.entities.CatalogEntity;
import com.ofertas.repository.CatalogRepository;

@Service("catalogService")
public class CatalogService {
	
	@Autowired
	@Qualifier("catalogRepository")
	private CatalogRepository catalogRepository;
	
	public ResponseEntity<Object> createCatalog (CatalogEntity catalogEntity) {
		try {
			catalogEntity.setCode(0);
			CatalogEntity catalogEntity2 = catalogRepository.save(catalogEntity);

			return new ResponseEntity<>(catalogEntity2, HttpStatus.OK);
			
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	public boolean removeCatalog (int code) {
		try {
			CatalogEntity catalogEntity = catalogRepository.findByCode(code);
			if (catalogEntity != null) {
				catalogRepository.delete(catalogEntity);
				return true;
			}
			return false;
		} catch (Exception e) {
			return false;
		}
		
	}
	
	public ResponseEntity<Object> updateCatalog (CatalogEntity catalogEntity) {
		try {
			CatalogEntity catalogEntity2 = catalogRepository.findByCode(catalogEntity.getCode());
			
			if (catalogEntity2 !=null) {
				CatalogEntity catalogEntity3 = catalogRepository.save(catalogEntity);
				return new ResponseEntity<>(catalogEntity3, HttpStatus.OK);
			}
			return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	public ResponseEntity<Object> findAllCatalogs () {
		try {
			List<CatalogEntity> catalogEntities = catalogRepository.findAll();
			return new ResponseEntity<>(catalogEntities, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	public ResponseEntity<Object> findById(int id) {
		try {
			CatalogEntity catalogEntity = catalogRepository.findByCode(id);
			return new ResponseEntity<>(catalogEntity, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	public ResponseEntity<Object> findBySupplierDocument(String document) {
		try {
			List<CatalogEntity> catalogEntities = catalogRepository.findBySupplierDocument(document);
			return new ResponseEntity<Object>(catalogEntities, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
