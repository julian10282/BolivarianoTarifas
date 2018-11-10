package com.ofertas.repository;

import com.ofertas.entities.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository("clientRepository")
public interface ClientRepository extends JpaRepository<ClientEntity, Serializable> {
	
	public ClientEntity findByDocument(int document);
}
