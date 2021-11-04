package com.tcc.barbecuenow.cart.domain.product;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class FinalProduct {
    @JsonProperty("product")
    private Product product;

    @JsonProperty("quantity")
    private int quantity;

    @JsonProperty("price")
    private Double price;
}
