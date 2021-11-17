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
    private String street;

    private String number;

    private String cep;

    private String district;

    private String city;

    private String state;
}
