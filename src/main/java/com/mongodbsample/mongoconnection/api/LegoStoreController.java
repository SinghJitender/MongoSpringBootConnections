package com.mongodbsample.mongoconnection.api;

import com.mongodbsample.mongoconnection.datamodel.LegoSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("legostore/api")
public class LegoStoreController {
  @Autowired
  private MongoTemplate template;

  @PostMapping
  public String insert(@RequestBody LegoSet legoSet){
    try {
      this.template.insert(legoSet);
      return "Success";
    }catch ( Exception e){
      return e.toString();
    }
  }

  @DeleteMapping("/{id}")
  public String delete(@PathVariable String id) {
    try {
      this.template.remove(new Query(Criteria.where("id").is(id)),LegoSet.class);
      return "Success";
    }catch ( Exception e){
      return e.toString();
    }
  }

  @PutMapping()
  public String update(@RequestBody LegoSet legoSet) {
    try {
      this.template.save(legoSet);
      return "Success";
    }catch ( Exception e){
      return e.toString();
    }
  }

  @GetMapping("/all")
  public Collection<LegoSet> getAll(){
    return template.findAll(LegoSet.class);
  }
}
