package com.mongodbsample.mongoconnection.persistence;

import com.mongodbsample.mongoconnection.datamodel.AverageRatingModel;
import com.mongodbsample.mongoconnection.datamodel.LegoSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.ProjectionOperation;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.newAggregation;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.project;

@Service
public class ReportService {
  @Autowired
  private MongoTemplate mongoTemplate;

  public List<AverageRatingModel> getAverageRatingReport() {
    ProjectionOperation projectionOperation = project()
              .andExpression("name").as("productName")
              .andExpression("{$avg : '$productReviews.rating'}").as("avgRating");
    Aggregation avgAggregation = newAggregation(LegoSet.class, projectionOperation);

    return mongoTemplate.aggregate(avgAggregation, LegoSet.class, AverageRatingModel.class).getMappedResults();
  }

}
