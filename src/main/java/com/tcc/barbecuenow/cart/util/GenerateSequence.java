package com.tcc.barbecuenow.cart.util;

import com.tcc.barbecuenow.cart.domain.order.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;
import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;

@Service
public class GenerateSequence {

    @Autowired private static MongoOperations mongo;

    public static int getNextSequence(int seqId) {

        Order counter = mongo.findAndModify(
                query(where("_id").is(seqId)),
                new Update().inc("orderNumber",1),
                options().returnNew(true).upsert(true),
                Order.class);

        return counter.getOrderNumber();
    }
}
