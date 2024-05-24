package com.collectorhub.collectorhub;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = "com.collectorhub.collectorhub")
@EntityScan(basePackages = "com.collectorhub.collectorhub.database.entities")
@EnableJpaRepositories(basePackages = "com.collectorhub.collectorhub.database.repositories")
public class CollectorhubApplication {

	public static void main(String[] args) {
		SpringApplication.run(CollectorhubApplication.class, args);
	}

}
