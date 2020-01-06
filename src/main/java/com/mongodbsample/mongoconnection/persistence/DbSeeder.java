package com.mongodbsample.mongoconnection.persistence;

import com.mongodbsample.mongoconnection.datamodel.DeliveryInfo;
import com.mongodbsample.mongoconnection.datamodel.LegoSet;
import com.mongodbsample.mongoconnection.datamodel.LegoSetDifficuty;
import com.mongodbsample.mongoconnection.datamodel.ProductReview;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collection;

@Service
public class DbSeeder implements CommandLineRunner {

  private MongoTemplate mongoTemplate;

  private DbSeeder(MongoTemplate mongoTemplate) {
    this.mongoTemplate = mongoTemplate;
  }

  @Override
  public void run(String... args) throws Exception {

   // this.mongoTemplate.dropCollection(LegoSet.class);

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

    LegoSet skyPolice =  new LegoSet(
      "Sky Police Air Base",
      "City",
      new DeliveryInfo(LocalDate.now().plusDays(3),50, true),
      LegoSetDifficuty.MEDIUM,
      Arrays.asList(
        new ProductReview("Dan",5),
        new ProductReview("Andrew",8.2)
      ));

    LegoSet mindStromsEve =  new LegoSet(
      "MINDSTROMS Ev3",
      "MindStroms",
      new DeliveryInfo(LocalDate.now().plusDays(10),100, false),
      LegoSetDifficuty.HARD,
      Arrays.asList(
        new ProductReview("Jitu",9.0),
        new ProductReview("Mani",7.1),
        new ProductReview("Tom",8.2)
      ));

    //Collection<LegoSet> initialProducts = Arrays.asList(milleniumFalcom,mcLarenSenna,skyPolice,mindStromsEve);
    //this.mongoTemplate.insertAll(initialProducts);
  }
}
