package com.paymentprocessor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class PaymentprocessorApplication {

	public static void main(String[] args) {
		SpringApplication.run(PaymentprocessorApplication.class, args);
	}

}
