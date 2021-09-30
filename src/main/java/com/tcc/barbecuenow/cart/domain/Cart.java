package com.tcc.barbecuenow.cart.domain;

import lombok.*;
import org.apache.tomcat.jni.Local;
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
        if (item != null && items.contains(item)) {
            items.stream()
                    .filter(s -> s.getId().equals(item.getId()))
                    .findFirst().ifPresent(oldCartItem -> oldCartItem.setQuantity(item.getQuantity()));
        } else {
            items.add(item);
        }
    }

    public void removeProductFromCart(String productId) {
        items.stream()
                .filter(s -> s.getId().equals(productId))
                .findFirst().ifPresent(cartItem -> items.remove(cartItem));
    }

    public void updateProductQuantity(CartItem item) {
        items.stream()
                .filter(s -> s.getId().equals(item.getId()))
                .findFirst().ifPresent(cartItem -> cartItem.setQuantity(item.getQuantity()));
    }
}
