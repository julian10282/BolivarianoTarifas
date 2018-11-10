package com.ofertas.repository;

import com.ofertas.entities.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

@Repository("productRepository")
public interface ProductRepository extends JpaRepository<ProductEntity, Serializable> {
	
	public ProductEntity findByCode (int id);
	
	public List<ProductEntity> findByCatalogCode (int cod);
}
