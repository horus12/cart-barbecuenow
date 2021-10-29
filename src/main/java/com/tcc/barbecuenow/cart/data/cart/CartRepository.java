package com.tcc.barbecuenow.cart.data.cart;

import com.tcc.barbecuenow.cart.domain.cart.Cart;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CartRepository extends MongoRepository<Cart, String> {

    Cart findByEmail(String Email);
}
