package com.tcc.barbecuenow.cart.controller.domain.request.order;

import com.tcc.barbecuenow.cart.domain.order.Address;
import lombok.Data;
import lombok.NonNull;

@Data
public class OrderRequest {
    @NonNull
    private String userId;

    @NonNull
    private PaymentTypeRequest paymentTypeRequest;

    @NonNull
    private Address address;
}

