package com.mongodbsample.mongoconnection.datamodel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/*
*@document tells spring that this class is a mongoDB document and
* collection="LegoSets" define the colletions where this document will be stored
*/
@Document(collection = "LegoSets")
public class LegoSet {
  //@Id defines the ID for the document
  @Id
  private String id;
  private String name;
  /*
  @Indexed annotation creates an index on theme. Which later processes information fast if we need to filter LegoSets based upon theme.
  @IndexDirection specifies the order in which the items are stored.
  We should use @Indexed on items which we feel like we're going to query alot.
  Indexing also helps in retreving data faster but it also slows down when we update,delete or add new data.
  This causes performace degradation in other operations. Just index things that you're going to query alot.
   */
  @Indexed(direction = IndexDirection.ASCENDING)
  private String theme;
  // @Field() annotation tells mongo that deliveryInfo will be stored with name as "delivery"
  @Field("delivery")
  private DeliveryInfo deliveryInfo;
  private LegoSetDifficuty legoSetDifficuty;
  private Collection<ProductReview> productReviews = new ArrayList<>();

  /*@PersistenceConstructor annotation is used to specify which constructor
   * to use (in case of multiple constructor) while serializinr
   * or de-serializing objects. This is not needed in case on single constructor */
  @PersistenceConstructor
  public LegoSet(String name, String theme, DeliveryInfo deliveryInfo,
                 LegoSetDifficuty legoSetDifficuty, Collection<ProductReview> productReviews) {
    this.name = name;
    this.theme = theme;
    this.deliveryInfo = deliveryInfo;
    this.legoSetDifficuty = legoSetDifficuty;
    if (productReviews != null) {
      this.productReviews = productReviews;
    }
  }
  //@Transient annotation helps in ignoring the field that should not be added to the mongoDB document/collection.
  @Transient
  private int nbParts;

  public String getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getTheme() {
    return theme;
  }

  public DeliveryInfo getDeliveryInfo() {
    return deliveryInfo;
  }

  public LegoSetDifficuty getLegoSetDifficuty() {
    return legoSetDifficuty;
  }

  public Collection<ProductReview> getProductReviews() {
    return Collections.unmodifiableCollection(this.productReviews);
  }

  public int getNbParts() {
    return nbParts;
  }
}
