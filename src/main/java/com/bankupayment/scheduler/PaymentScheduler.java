package com.bankupayment.scheduler;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.bankupayment.entities.PaymentEntity;
import com.bankupayment.services.PaymentService;

@Component
public class PaymentScheduler {
	
	private static final Log LOG = LogFactory.getLog(PaymentScheduler.class);
	 
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
	
	@Autowired
	@Qualifier("paymentService")
	private PaymentService paymentService;
	
	@Scheduled(fixedRate = 60000)
	public void payScheduledPayments() {
		LOG.info("The time is now " + dateFormat.format(new Date()));
		List<PaymentEntity> paymentEntities = paymentService.findPaymentByPaymentDate();
		
		if (paymentEntities != null) {
			LOG.info("Total pays to do [Total="+ paymentEntities.size()+"]");
			
			for (PaymentEntity paymentEntity : paymentEntities) {
				LOG.info("Doing Pay [Payment="+ paymentEntity.toString() +"]");
				ResponseEntity<Object> responseEntity = paymentService.pay(paymentEntity);
				LOG.info("Pay result [Status="+ responseEntity.getStatusCode() +"]");
				
				if (responseEntity.getStatusCode() == HttpStatus.OK) {
					
					PaymentEntity paymentEntity2 = (PaymentEntity) responseEntity.getBody();
					
					Calendar myCal = Calendar.getInstance();
				    myCal.setTime(paymentEntity2.getPaymentDate());    
				    myCal.add(Calendar.MONTH, +1);
				
					paymentEntity2.setPaymentDate(myCal.getTime());
					paymentService.updatePayment(paymentEntity2);
				}
			}
		} else {
			LOG.info("Nothing to pay :(");
		}
	}

}
