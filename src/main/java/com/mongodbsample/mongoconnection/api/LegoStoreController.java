package com.mongodbsample.mongoconnection.api;

import com.mongodbsample.mongoconnection.datamodel.LegoSet;
import com.mongodbsample.mongoconnection.datamodel.LegoSetDifficuty;
import com.mongodbsample.mongoconnection.persistence.LegoSetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;

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

}
