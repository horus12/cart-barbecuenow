package com.tcc.barbecuenow.cart.controller.domain.request.order;

import lombok.Data;

@Data
public class PaymentTypeRequest {

    private CardPaymentRequest cardPaymentRequest;

    private String homePaymentRequest;
}
