package com.ofertas.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ofertas.entities.OfferEntity;

@Repository("offerRepository")
public interface OffertRepository extends JpaRepository<OfferEntity, Serializable> {
	
	public OfferEntity findById (int id);
	
	public List<OfferEntity> findByRequestId (int id);
}
