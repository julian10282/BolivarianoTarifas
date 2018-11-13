package com.bankupayment.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.bankupayment.entities.PaymentEntity;
import com.bankupayment.entities.TransationEntity;
import com.bankupayment.enumerator.PaymentTypeEnum;
import com.bankupayment.enumerator.StatusEnum;
import com.bankupayment.repository.PaymentRepository;

@Service("paymentService")
public class PaymentService {

	@Autowired
	@Qualifier("paymentRepository")
	private PaymentRepository paymentRepository;
	
	@Autowired
	@Qualifier("transactionService")
	private TransactionService transactionService;
	
	public ResponseEntity<Object> createPay (PaymentEntity paymentEntity) {
		try {
			paymentEntity.setCreateDate(new Date());
			paymentEntity.setModifyDate(new Date());
			paymentEntity.setEnabled(true);
			if (paymentEntity.getPaymentType() == PaymentTypeEnum.INTERNATIONAL_PAY.getPaymentType()) {
				paymentEntity.setSwiftCode("UNBKUSNY");
			}
			
			PaymentEntity paymentEntity2 = paymentRepository.save(paymentEntity);

			return new ResponseEntity<>(paymentEntity2, HttpStatus.OK);
			
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	public ResponseEntity<Object> findByAccount (String account) {
		try {
			List<PaymentEntity> paymentEntities = paymentRepository.findByOriginAccount(account);
			return new ResponseEntity<>(paymentEntities, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	public ResponseEntity<Object> updatePayment (PaymentEntity paymentEntity) {
		try {
			PaymentEntity paymentEntity2 = paymentRepository.findById(paymentEntity.getId());
		
			if (paymentEntity2 !=null) {
				paymentEntity.setCreateDate(paymentEntity2.getCreateDate());
				paymentEntity.setModifyDate(new Date());
				PaymentEntity paymentEntity3 = paymentRepository.save(paymentEntity);
				return new ResponseEntity<>(paymentEntity3, HttpStatus.OK);
			}
			return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	public List<PaymentEntity> findPaymentByPaymentDate(){
		try {
			return paymentRepository.findByPaymentDateBefore(new Date());
		} catch (Exception e) {
			return new ArrayList<>();
		}
	}
	
	public ResponseEntity<Object> pay(PaymentEntity paymentEntity) {
		try {
			
			// Verificar saldo en la cuenta
			
			// Si hay saldo descontar y realizar el pago
			if (paymentEntity.getId() == 0) {
				ResponseEntity<Object> responseEntity = createPay(paymentEntity);
				if (responseEntity.hasBody()) {
					paymentEntity = (PaymentEntity) responseEntity.getBody();
				}
			}
			
			//Si el pago falla regresa dinero a la cuenta
			
			PaymentEntity paymentEntitySaved = paymentEntity;
			
			TransationEntity transationEntity = new TransationEntity();
			transationEntity.setTransactionIdResponse(generarValorAleatorio().toString());
			transationEntity.setStatus(StatusEnum.SUCCESSS.getStatusType());
			transationEntity.setPaymentId(paymentEntitySaved.getId());
			
			TransationEntity transationEntitySaved = transactionService.createTransaction(transationEntity);
			
			if (transationEntitySaved != null) {
				List<TransationEntity> transationEntities = new ArrayList<>();
				transationEntities.add(transationEntitySaved);
				paymentEntitySaved.setTransations(transationEntities);
			}
			
			return new ResponseEntity<>(paymentEntitySaved, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	public Long generarValorAleatorio() {
		return (long)(Math.random() * 999999999) + 1;
	}
}
