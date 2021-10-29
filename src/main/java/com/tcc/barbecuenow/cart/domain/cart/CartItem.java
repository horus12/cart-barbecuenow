package com.tcc.barbecuenow.cart.domain.cart;

import lombok.*;
import org.springframework.data.annotation.Id;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartItem {

    @Id
    private String Id;

    private int Quantity;

    private String name;

    private String defaultImage;

    private Double price;

}
