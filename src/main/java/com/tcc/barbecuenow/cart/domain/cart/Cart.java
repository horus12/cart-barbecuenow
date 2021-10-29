package com.tcc.barbecuenow.cart.domain.cart;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

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

        AtomicBoolean alreadyAdded = new AtomicBoolean(false);
        items.forEach(product -> {
            if (product.getId().equals(item.getId()))
                alreadyAdded.set(true);
        });

        if (item != null && alreadyAdded.get()) {
            items.stream()
                    .filter(s -> s.getId().equals(item.getId()))
                    .findFirst().ifPresent(oldCartItem -> oldCartItem.setQuantity(oldCartItem.getQuantity() + item.getQuantity()));
        } else {
            items.add(item);
        }
        calculateTotalValue();
    }

    public void removeProductFromCart(String productId) {
        items.stream()
                .filter(s -> s.getId().equals(productId))
                .findFirst().ifPresent(cartItem -> items.remove(cartItem));
        calculateTotalValue();
    }

    public void updateProductQuantity(CartItem item) {
        items.stream()
                .filter(s -> s.getId().equals(item.getId()))
                .findFirst().ifPresent(cartItem -> cartItem.setQuantity(item.getQuantity()));
        calculateTotalValue();
    }

    public void calculateTotalValue(){
        this.totalValue = 0;
        for(CartItem item: items){
            this.totalValue += item.getPrice() * item.getQuantity();
        }
    }
}
