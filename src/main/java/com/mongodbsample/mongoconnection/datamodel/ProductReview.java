package com.mongodbsample.mongoconnection.datamodel;

import org.springframework.data.mongodb.core.index.TextIndexed;

public class ProductReview {
  @TextIndexed
  private String userName;
  private double rating;

  public ProductReview(String userName, double rating) {
    this.userName = userName;
    this.rating = rating;
  }

  public String getUserName() {
    return userName;
  }

  public double getRating() {
    return rating;
  }
}
