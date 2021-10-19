package com.tcc.barbecuenow.cart.domain.order;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Address {
    private String address;
    private String zipCode;
    private String complement;
    private int number;
}
