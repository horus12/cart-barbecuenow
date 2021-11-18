package com.tcc.barbecuenow.cart.domain.order;

import com.tcc.barbecuenow.cart.domain.cart.CartItem;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Document(collection = "orders")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Order implements Comparable<Order>{

    @Id
    private String id;

    private String status;

    private String userId;

    private String userName;

    private String email;

    private Double totalPrice;

    private Double subTotalPrice;

    private Double tax;

    private Address address;

    private List<CartItem> products;

    private int orderNumber;

    private PaymentType paymentType;

    private String rejectJustification;

    private LocalDateTime createdDate;

    private Boolean isFinished;

    @Override
    public int compareTo(Order o) {
        return getCreatedDate().compareTo(o.getCreatedDate());
    }
}
