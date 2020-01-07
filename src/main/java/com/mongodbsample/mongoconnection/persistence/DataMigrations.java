package com.mongodbsample.mongoconnection.persistence;

import com.github.mongobee.changeset.ChangeLog;
import com.github.mongobee.changeset.ChangeSet;
import com.mongodbsample.mongoconnection.datamodel.LegoSet;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

@ChangeLog(order = "001")
public class DataMigrations {

    @ChangeSet(order= "001", author = "jitender", id="update nb parts")
    public void updateNbParts(MongoTemplate template) {
        Criteria criteria = new Criteria().orOperator(
                Criteria.where("nbParts").is(0),
                Criteria.where("nbParts").is(null)
        );
        template.updateMulti(new Query(criteria),
                Update.update("nbParts",122),
                LegoSet.class);

        System.out.println("Applied Changeset 001");
    }

    @ChangeSet(order= "002", author = "jitender", id="update nb parts second time")
    public void updateNbPartsSecondTime(MongoTemplate template) {
        Criteria criteria = new Criteria().orOperator(
                Criteria.where("nbParts").is(0),
                Criteria.where("nbParts").is(null)
        );
        template.updateMulti(new Query(criteria),
                Update.update("nbParts",122),
                LegoSet.class);

        System.out.println("Applied Changeset 002");
    }
}
