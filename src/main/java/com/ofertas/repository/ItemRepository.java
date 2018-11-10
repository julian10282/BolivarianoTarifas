package com.ofertas.repository;

import com.ofertas.entities.ItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

@Repository("itemRepository")
public interface ItemRepository extends JpaRepository<ItemEntity, Serializable> {
	
	public ItemEntity findById(int id);
	
	public List<ItemEntity> findByRequestId(int requestid);

	public List<ItemEntity> findByOfferId(int id);
}
