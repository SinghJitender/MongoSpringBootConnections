package com.mongodbsample.mongoconnection.datamodel;

public class AverageRatingModel {
  private String id;
  private String productName;
  private double avgRating;

  public String getId() {
    return id;
  }

  public String getProductName() {
    return productName;
  }

  public double getAvgRating() {
    return avgRating;
  }
}
