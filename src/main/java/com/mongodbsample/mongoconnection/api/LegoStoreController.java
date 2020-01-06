package com.mongodbsample.mongoconnection.api;

import com.mongodbsample.mongoconnection.datamodel.LegoSet;
import com.mongodbsample.mongoconnection.datamodel.LegoSetDifficuty;
import com.mongodbsample.mongoconnection.datamodel.QLegoSet;
import com.mongodbsample.mongoconnection.persistence.LegoSetRepository;
import com.querydsl.core.types.dsl.BooleanExpression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("legostore/api")
public class LegoStoreController {
  @Autowired
  private MongoTemplate template;
  @Autowired
  private LegoSetRepository legoSetRepository;

  @PostMapping
  public String insert(@RequestBody LegoSet legoSet){
    try {
      legoSetRepository.insert(legoSet);
      //this.template.insert(legoSet);
      return "Success";
    }catch ( Exception e){
      return e.toString();
    }
  }

  @DeleteMapping("/{id}")
  public String delete(@PathVariable String id) {
    try {
      legoSetRepository.deleteById(id);
      //this.template.remove(new Query(Criteria.where("id").is(id)),LegoSet.class);
      return "Success";
    }catch ( Exception e){
      return e.toString();
    }
  }

  @PutMapping()
  public String update(@RequestBody LegoSet legoSet) {
    try {
      legoSetRepository.save(legoSet);
      //this.template.save(legoSet);
      return "Success";
    }catch ( Exception e){
      return e.toString();
    }
  }

  @GetMapping("/all")
  public Collection<LegoSet> getAll(){
    return legoSetRepository.findAll();
    //return template.findAll(LegoSet.class);
  }

  @GetMapping("/{id}")
  public Optional<LegoSet> findById(@PathVariable String id) {
      return legoSetRepository.findById(id);
  }

  @GetMapping("theme/{theme}")
  public Collection<LegoSet> findByTheme(@PathVariable String theme) {
    //return legoSetRepository.findAllByThemeContains(theme);
    return legoSetRepository.findAllByTheme(theme);
  }


  @GetMapping("findByThemeContains/{theme}")
  public Collection<LegoSet> findByThemeContains(@PathVariable String theme) {
    //return legoSetRepository.findAllByThemeContains(theme);
    return legoSetRepository.findAllByThemeContains(theme);
  }

  @GetMapping("findByThemeSorted/{theme}")
  public Collection<LegoSet> findByThemeSorted(@PathVariable String theme) {
    //return legoSetRepository.findAllByThemeContains(theme);
    Sort sortByTheme = Sort.by("theme").ascending();
    return legoSetRepository.findAllByThemeContains(theme,sortByTheme);
  }

  @GetMapping("difficulty/{difficulty}")
  public Collection<LegoSet> findByDifficulty(@PathVariable LegoSetDifficuty difficulty) {
    //return legoSetRepository.findAllByThemeContains(theme);
    return legoSetRepository.findAllByLegoSetDifficuty(difficulty);
  }

  @GetMapping("hardAndStartsWithM")
  public Collection<LegoSet> hardAndStartsWithM() {
    //return legoSetRepository.findAllByThemeContains(theme);
    return legoSetRepository.findAllByLegoSetDifficutyAndNameStartsWith(LegoSetDifficuty.HARD,"M");
  }

  @GetMapping("byDeliveryFeeLessThan/{price}")
  public Collection<LegoSet> byDeliveryFeeLessThan(@PathVariable int price) {
    return legoSetRepository.findAllByDeliveryPriceLessThan(price);
  }

  @GetMapping("byProductReviews/{ratings}")
  public Collection<LegoSet> byProductReviews(@PathVariable int ratings) {
    return legoSetRepository.findAllByProductRatings(ratings);
  }

  @GetMapping("/allSortedByTheme")
  public Collection<LegoSet> getAllSortedByTheme(){
    Sort sortByTheme = Sort.by("theme").ascending();
    return legoSetRepository.findAll(sortByTheme);
    //return template.findAll(LegoSet.class);
  }

  @GetMapping("bestBuys")
  public Collection<LegoSet> bestBuys() {

    QLegoSet query = new QLegoSet("query");
    BooleanExpression inStockFilter = query.deliveryInfo().inStock.isTrue();
    BooleanExpression deliveryFeeFilter = query.deliveryInfo().deliveryFee.lt(50);
    BooleanExpression productReviewsFilter = query.productReviews.any().rating.eq(10);

    BooleanExpression bestBuysFilter = inStockFilter.and(deliveryFeeFilter).and(productReviewsFilter);

    return (Collection<LegoSet>) legoSetRepository.findAll(bestBuysFilter);

  }

  @GetMapping("fullTextSearch/{text}")
  public Collection<LegoSet> fullTextSearch(@PathVariable String text){
    TextCriteria criteria = TextCriteria.forDefaultLanguage().matching(text);
    return legoSetRepository.findAllBy(criteria);
  }
  
}
