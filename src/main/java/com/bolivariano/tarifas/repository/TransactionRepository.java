package com.bolivariano.tarifas.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bolivariano.tarifas.entities.TransationEntity;

@Repository("transactionRepository")
public interface TransactionRepository extends JpaRepository<TransationEntity, Serializable> {
	
}
