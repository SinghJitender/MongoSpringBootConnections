package com.mongodbsample.mongoconnection.persistence;

import com.mongodbsample.mongoconnection.datamodel.LegoSet;
import com.mongodbsample.mongoconnection.datamodel.LegoSetDifficuty;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.web.SortDefault;

import java.util.Collection;

public interface LegoSetRepository extends MongoRepository<LegoSet, String>, QuerydslPredicateExecutor<LegoSet> {
    Collection<LegoSet> findAllByThemeContains(String theme);
    Collection<LegoSet> findAllByTheme(String theme);
    Collection<LegoSet> findAllByThemeContains(String theme, Sort sort);
    Collection<LegoSet> findAllByLegoSetDifficuty(LegoSetDifficuty legoSetDifficuty);
    Collection<LegoSet> findAllByLegoSetDifficutyAndNameStartsWith(LegoSetDifficuty legoSetDifficuty, String name);
    Collection<LegoSet> findAllBy(TextCriteria criteria);

    @Query("{'deliveryInfo.deliveryFee' : {$lt : ?0}}")
    Collection<LegoSet> findAllByDeliveryPriceLessThan(int price);

    @Query("{'productReviews.rating' : {$eq : ?0}}")
    Collection<LegoSet> findAllByProductRatings(int rating);

    @Query("{'paymentOptions.id' : ?0 }")
    Collection<LegoSet> findAllPaymentOptionId(String id);
}
