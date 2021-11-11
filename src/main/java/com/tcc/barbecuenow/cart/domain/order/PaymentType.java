package com.tcc.barbecuenow.cart.domain.order;

import com.tcc.barbecuenow.cart.controller.domain.request.order.CardPaymentRequest;
import com.tcc.barbecuenow.cart.controller.domain.request.order.HomePaymentRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class PaymentType {

    private CardPaymentRequest cardPaymentRequest;

    private HomePaymentRequest homePaymentRequest;
}
