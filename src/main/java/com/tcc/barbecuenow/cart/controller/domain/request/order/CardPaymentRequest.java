package com.tcc.barbecuenow.cart.controller.domain.request.order;

import lombok.Data;
import lombok.NonNull;

@Data
public class CardPaymentRequest {

    @NonNull
    private String number;

    @NonNull
    private String date;

    @NonNull
    private String cvv;

    @NonNull
    private String titularName;

    @NonNull
    private String cpf;
}
