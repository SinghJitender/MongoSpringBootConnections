package com.mongodbsample.mongoconnection.datamodel;

public class ProductReview {
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
