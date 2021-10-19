package com.tcc.barbecuenow.cart.data.order.sequence;

import com.tcc.barbecuenow.cart.domain.orderSequence.Sequence;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SequenceMongoRepository extends MongoRepository<Sequence, String> {

}
