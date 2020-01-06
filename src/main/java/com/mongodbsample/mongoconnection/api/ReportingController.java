package com.mongodbsample.mongoconnection.api;

import com.mongodbsample.mongoconnection.datamodel.AverageRatingModel;
import com.mongodbsample.mongoconnection.persistence.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("legostore/api/reports")
public class ReportingController {
  @Autowired
  private ReportService reportService;

  @GetMapping("/getAverageRatings")
  public List<AverageRatingModel> getAverageRatings(){
    return reportService.getAverageRatingReport();
  }
}
