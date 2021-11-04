package com.tcc.barbecuenow.cart.domain.product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class ProductList {
    List<FinalProduct> products;
}
