package com.ofertas.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ofertas.entities.ItemEntity;
import com.ofertas.repository.ItemRepository;

@Service("itemService")
public class ItemService {
	
	@Autowired
	@Qualifier("itemRepository")
	private ItemRepository itemRepository;
	
	public ResponseEntity<Object> createItem (ItemEntity itemEntity) {
		try {
			itemEntity.setId(0);
			ItemEntity itemEntity2 = itemRepository.save(itemEntity);

			return new ResponseEntity<>(itemEntity2, HttpStatus.OK);
			
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	public boolean removeItem (int code) {
		try {
			ItemEntity itemEntity = itemRepository.findById(code);
			if (itemEntity != null) {
				itemRepository.delete(itemEntity);
				return true;
			}
			return false;
		} catch (Exception e) {
			return false;
		}
	}
	
	public ResponseEntity<Object> updateItem (ItemEntity itemEntity) {
		try {
			ItemEntity itemEntity2 = itemRepository.findById(itemEntity.getId());
			
			if (itemEntity2 !=null) {
				ItemEntity itemEntity3 = itemRepository.save(itemEntity);
				return new ResponseEntity<>(itemEntity3, HttpStatus.OK);
			}
			return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	public ResponseEntity<Object> findAllItems () {
		try {
			List<ItemEntity> itemEntities = itemRepository.findAll();
			return new ResponseEntity<>(itemEntities, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	public ResponseEntity<Object> findByRequestId (int requestId) {
		try {
			List<ItemEntity> itemEntities = itemRepository.findByRequestId(requestId);
			return new ResponseEntity<>(itemEntities, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
