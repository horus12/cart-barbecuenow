package com.tcc.barbecuenow.cart.data.tax;

import com.tcc.barbecuenow.cart.domain.Tax.Tax;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TaxRepository extends MongoRepository<Tax, String> {

}
