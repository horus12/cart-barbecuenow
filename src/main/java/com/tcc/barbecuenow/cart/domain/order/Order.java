package com.tcc.barbecuenow.cart.domain.order;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Document(collection = "orders")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Order {

    @Id
    private String id;

    private String status;

    private String userId;

    private Double totalPrice;

    private Double totalTax;

    private Address address;

    private List<Item> products;

    private int orderNumber;

    private String rejectJustification;

    private LocalDateTime createdDate;
}
