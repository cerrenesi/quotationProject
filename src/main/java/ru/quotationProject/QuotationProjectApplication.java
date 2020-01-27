package ru.quotationProject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class QuotationProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(QuotationProjectApplication.class, args);
	}

}
