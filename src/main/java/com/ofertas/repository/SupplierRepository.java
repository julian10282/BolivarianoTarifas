package com.ofertas.repository;

import com.ofertas.entities.SupplierEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository("supplierRepository")
public interface SupplierRepository extends JpaRepository<SupplierEntity, Serializable> {
	
	public SupplierEntity findByDocument(int document);
}
