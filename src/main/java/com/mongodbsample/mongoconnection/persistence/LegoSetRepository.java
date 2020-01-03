package com.mongodbsample.mongoconnection.persistence;

import com.mongodbsample.mongoconnection.datamodel.LegoSet;
import com.mongodbsample.mongoconnection.datamodel.LegoSetDifficuty;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Collection;
import java.util.Optional;

public interface LegoSetRepository extends MongoRepository<LegoSet, String> {

    Collection<LegoSet> findAllByThemeContains(String theme);
    Collection<LegoSet> findAllByTheme(String theme);
    Collection<LegoSet> findAllByLegoSetDifficuty(LegoSetDifficuty legoSetDifficuty);
    Collection<LegoSet> findAllByLegoSetDifficutyAndNameStartsWith(LegoSetDifficuty legoSetDifficuty, String name);

    @Query("{'deliveryInfo.deliveryFee' : {$lt : ?0}}")
    Collection<LegoSet> findAllByDeliveryPriceLessThan(int price);

    @Query("{'productReviews.rating' : {$eq : ?0}}")
    Collection<LegoSet> findAllByProductRatings(int rating);
}
