package com.Projects.MedStore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
@ComponentScan(basePackages = {"com.Projects.MedStore"})
@EnableScheduling
public class MedStoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(MedStoreApplication.class, args);
	}

}
 