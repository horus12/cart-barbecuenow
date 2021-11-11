package com.tcc.barbecuenow.cart.usecase.payment;

import com.tcc.barbecuenow.cart.controller.domain.request.order.CardPaymentRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PaymentCardValidationUseCase {

    public boolean execute(CardPaymentRequest request) {

        if (request.getNumber().matches("1111(.*)"))
            return true;
        else return request.getNumber().matches("5555(.*)");
    }
}
