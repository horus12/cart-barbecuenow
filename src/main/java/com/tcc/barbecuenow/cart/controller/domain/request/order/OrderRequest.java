package com.tcc.barbecuenow.cart.controller.domain.request.order;

import com.tcc.barbecuenow.cart.domain.order.Address;
import com.tcc.barbecuenow.cart.domain.order.Item;
import lombok.Data;
import lombok.NonNull;
import java.util.List;

@Data
public class OrderRequest {
    @NonNull
    private String userId;

    @NonNull
    private Double totalPrice;

    @NonNull
    private Double totalTax;

    @NonNull
    private List<Item> products;

    @NonNull
    private Address address;

    private String status = "pending";
}

