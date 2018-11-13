package com.bankupayment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class BankuPaymentApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankuPaymentApplication.class, args);
	}
}
