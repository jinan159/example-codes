package com.example.springbatchstoredata;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableBatchProcessing
@SpringBootApplication
public class SpringBatchStoreDataApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBatchStoreDataApplication.class, args);
	}

}
