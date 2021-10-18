package com.tcc.barbecuenow.cart.domain.cart;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;

@Document(collection = "cart")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Cart {

    @Id
    private String id;

    private String email;

    private double totalValue;

    private LocalDate createdDate;

    private List<CartItem> items;

    public void addItemToCart(CartItem item) {
        items.add(item);
    }
}
