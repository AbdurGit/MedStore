package com.Projects.MedStore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@ComponentScan(basePackages = {"com.Projects.MedStore"})
public class MedStoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(MedStoreApplication.class, args);
	}

}
 