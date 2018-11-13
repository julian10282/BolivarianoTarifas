package com.bankupayment.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.bankupayment.entities.TransationEntity;
import com.bankupayment.repository.TransactionRepository;

@Service("transactionService")
public class TransactionService {

	@Autowired
	@Qualifier("transactionRepository")
	private TransactionRepository transactionRepository;
	
	public TransationEntity createTransaction (TransationEntity transationEntity) {
		try {
			transationEntity.setCreateDate(new Date());
			transationEntity.setModifyDate(new Date());
			
			return transactionRepository.save(transationEntity);
		} catch (Exception e) {
			return null;
		}
	}
	
}
