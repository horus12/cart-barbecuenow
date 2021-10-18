package com.tcc.barbecuenow.cart.data.order;

import com.tcc.barbecuenow.cart.domain.order.Order;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface OrderMongoRepository extends MongoRepository<Order, String> {
    List<Order> findAllByStatus(String status);

    Order save(final Order order);
}
