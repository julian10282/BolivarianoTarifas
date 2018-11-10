package com.ofertas.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ofertas.entities.ItemEntity;
import com.ofertas.entities.OfferEntity;
import com.ofertas.repository.ItemRepository;
import com.ofertas.repository.OffertRepository;

@Service("offerService")
public class OfferService {
	
	@Autowired
	@Qualifier("offerRepository")
	private OffertRepository offertRepository;
	
	@Autowired
	@Qualifier("itemRepository")
	private ItemRepository itemRepository;

	public ResponseEntity<Object> createOffer (OfferEntity offerEntity) {
		try {
			offerEntity.setId(0);
			OfferEntity offerEntity2 = offertRepository.save(offerEntity);
			
			if (offerEntity.getItemEntities() != null) {
				
				List<ItemEntity> itemEntities = new ArrayList<>();
				
				for (ItemEntity itemEntity : offerEntity.getItemEntities()) {
					itemEntity.setOfferId(offerEntity2.getId());
					ItemEntity itemEntity2 = itemRepository.save(itemEntity);
					itemEntities.add(itemEntity2);
				}
				
				offerEntity2.setItemEntities(itemEntities);
			}

			return new ResponseEntity<>(offerEntity2, HttpStatus.OK);
			
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	public boolean removeOffer (int id) {
		try {
			OfferEntity offerEntity = offertRepository.findById(id);
			if (offerEntity != null) {
				offertRepository.delete(offerEntity);
				return true;
			}
			return false;
		} catch (Exception e) {
			return false;
		}
	}
	
	public ResponseEntity<Object> updateOffer (OfferEntity offerEntity) {
		try {
			OfferEntity offerEntity2 = offertRepository.findById(offerEntity.getId());
			
			if (offerEntity2 !=null) {
				OfferEntity offerEntity3 = offertRepository.save(offerEntity);
				return new ResponseEntity<>(offerEntity3, HttpStatus.OK);
			}
			return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	public ResponseEntity<Object> findAllOffers () {
		try {
			List<OfferEntity> opEntities = offertRepository.findAll();
			return new ResponseEntity<>(opEntities, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	public ResponseEntity<Object> findOfferByRequestId (int requestId) {
		try {
			List<OfferEntity> offers = offertRepository.findByRequestId(requestId);
			
			for (OfferEntity offerEntity : offers) {
				List<ItemEntity> itemEntities = itemRepository.findByOfferId(offerEntity.getId());
				offerEntity.setItemEntities(itemEntities);
			}
			
			return new ResponseEntity<>(offers, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
