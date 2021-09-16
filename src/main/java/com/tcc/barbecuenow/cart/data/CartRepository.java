package com.tcc.barbecuenow.cart.data;

import com.tcc.barbecuenow.cart.domain.Cart;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CartRepository extends MongoRepository<Cart, String> {

}
