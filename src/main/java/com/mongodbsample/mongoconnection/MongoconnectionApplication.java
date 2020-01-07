package com.mongodbsample.mongoconnection;

import com.github.mongobee.Mongobee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;

@SpringBootApplication
public class MongoconnectionApplication {

	public static void main(String[] args) {
		SpringApplication.run(MongoconnectionApplication.class, args);
	}

	@Autowired
	MongoTemplate mongoTemplate;

	/** MongoBee can be used for data migration related tasks. */
	@Bean
	public Mongobee mongobee(){
		Mongobee runner = new Mongobee("mongodb://localhost:27017/legostore");
		runner.setMongoTemplate(mongoTemplate);         // host must be set if not set in URI
		runner.setChangeLogsScanPackage("com.mongodbsample.mongoconnection.persistence"); // the package to be scanned for changesets
		return runner;
	}

}
