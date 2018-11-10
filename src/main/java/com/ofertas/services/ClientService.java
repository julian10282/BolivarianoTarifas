package com.ofertas.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ofertas.entities.ClientEntity;
import com.ofertas.repository.ClientRepository;

@Service("clientService")
public class ClientService {

	@Autowired
	@Qualifier("clientRepository")
	private ClientRepository clientRepository;
	
	public ResponseEntity<Object> createClient (ClientEntity clientEntity) {
		try {
			clientEntity.setCreateDate(new Date());
			clientEntity.setModifyDate(new Date());
			ClientEntity clientEntity2 = clientRepository.save(clientEntity);

			return new ResponseEntity<>(clientEntity2, HttpStatus.OK);
			
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	public boolean removeClient (int document) {
		try {
			ClientEntity clientEntity = clientRepository.findByDocument(document);
			if (clientEntity != null) {
				clientRepository.delete(clientEntity);
				return true;
			}
			return false;
		} catch (Exception e) {
			return false;
		}
	}
	
	public ResponseEntity<Object> updateClient (ClientEntity clientEntity) {
		try {
			ClientEntity clientEntity2 = clientRepository.findByDocument(clientEntity.getDocument());
		
			if (clientEntity2 !=null) {
				clientEntity.setCreateDate(clientEntity2.getCreateDate());
				clientEntity.setModifyDate(new Date());
				ClientEntity clientEntity3 = clientRepository.save(clientEntity);
				return new ResponseEntity<>(clientEntity3, HttpStatus.OK);
			}
			return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	public ResponseEntity<Object> findAllClients () {
		try {
			List<ClientEntity> clientEntities = clientRepository.findAll();
			return new ResponseEntity<>(clientEntities, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
}
