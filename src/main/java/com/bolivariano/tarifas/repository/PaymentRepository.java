package com.bolivariano.tarifas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bolivariano.tarifas.entities.PaymentEntity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Repository("paymentRepository")
public interface PaymentRepository extends JpaRepository<PaymentEntity, Serializable> {
	public List<PaymentEntity> findByOriginAccount(String account);
	
	public PaymentEntity findById(int id);
	
	public List<PaymentEntity> findByPaymentDateBefore(Date paymentDate);
}
