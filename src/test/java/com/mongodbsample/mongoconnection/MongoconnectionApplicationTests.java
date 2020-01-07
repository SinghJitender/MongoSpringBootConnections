package com.mongodbsample.mongoconnection;

import com.mongodbsample.mongoconnection.datamodel.DeliveryInfo;
import com.mongodbsample.mongoconnection.datamodel.LegoSet;
import com.mongodbsample.mongoconnection.datamodel.LegoSetDifficuty;
import com.mongodbsample.mongoconnection.datamodel.ProductReview;
import com.mongodbsample.mongoconnection.persistence.LegoSetRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

/** Close MongoDb Service before running these tests. (Windows Search -> Service -> MongoDD Server -> Stop*/
@RunWith(SpringRunner.class)
@DataMongoTest
public class MongoconnectionApplicationTests {

	@Autowired
	private MongoTemplate mongoTemplate;

	@Autowired
	private LegoSetRepository legoSetRepository;

	@Before
	public void before() {
		legoSetRepository.deleteAll();;

		LegoSet milleniumFalcom =  new LegoSet(
				"Millenium Falcom",
				"Star Wars",
				new DeliveryInfo(LocalDate.now().plusDays(1),30, true),
				LegoSetDifficuty.HARD,
				Arrays.asList(
						new ProductReview("Dan",7),
						new ProductReview("Anne",10),
						new ProductReview("John",8.4)
				)
		);

		LegoSet mcLarenSenna =  new LegoSet(
				"McLaren Senna",
				"Speed Champions",
				new DeliveryInfo(LocalDate.now().plusDays(7),70, false),
				LegoSetDifficuty.EASY,
				Arrays.asList(
						new ProductReview("Prachi",9.3),
						new ProductReview("Sukhi",9.9)
				));

		legoSetRepository.insert(milleniumFalcom);
		legoSetRepository.insert(mcLarenSenna);

	}

	@Test
	public void findProductByRatings10Test(){
		List<LegoSet> legoSets = (List<LegoSet>) legoSetRepository.findAllByProductRatings(10);
		Assert.assertEquals(1,legoSets.size());
		Assert.assertEquals("Millenium Falcom",legoSets.get(0).getName());
	}

}
