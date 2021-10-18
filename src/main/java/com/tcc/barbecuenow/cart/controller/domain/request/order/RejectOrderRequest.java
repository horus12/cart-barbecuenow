package com.tcc.barbecuenow.cart.controller.domain.request.order;

import lombok.Data;
import lombok.NonNull;

@Data
public class RejectOrderRequest {

    @NonNull
    private String rejectJustification;
}
