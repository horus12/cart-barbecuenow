package com.tcc.barbecuenow.cart.usecase.order;

import com.tcc.barbecuenow.cart.controller.domain.request.order.HomePaymentRequest;
import com.tcc.barbecuenow.cart.controller.domain.request.order.OrderRequest;
import com.tcc.barbecuenow.cart.controller.domain.request.order.PaymentTypeRequest;
import com.tcc.barbecuenow.cart.data.cart.CartRepository;
import com.tcc.barbecuenow.cart.data.order.OrderMongoRepository;
import com.tcc.barbecuenow.cart.domain.cart.Cart;
import com.tcc.barbecuenow.cart.domain.order.Order;
import com.tcc.barbecuenow.cart.domain.order.OrderStatus;
import com.tcc.barbecuenow.cart.domain.order.PaymentType;
import com.tcc.barbecuenow.cart.usecase.orderSequence.OrderSequenceUseCase;
import com.tcc.barbecuenow.cart.usecase.payment.PaymentCardValidationUseCase;
import com.tcc.barbecuenow.cart.util.TokenHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

@RequiredArgsConstructor
@Service
public class CreateOrderUseCase {

    private final OrderMongoRepository orderMongoRepository;
    private final OrderSequenceUseCase orderSequenceUseCase;
    private final CartRepository cartRepository;
    private final TokenHelper tokenHelper;
    private final PaymentCardValidationUseCase paymentCardValidationUseCase;

    public Order execute(OrderRequest request) throws Exception {
        String email = tokenHelper.execute(request.getUserId());
        if (email == null) throw new Exception("invalid_token");

        Cart cart = cartRepository.findByEmail(email);

        if (cart == null) throw new Exception("cart_not_found");

        if (request.getPaymentTypeRequest().getHomePaymentRequest() == null) {
            if (!paymentCardValidationUseCase.execute(request.getPaymentTypeRequest().getCardPaymentRequest()))
                throw new Exception("card_rejected");
        }

        String name = tokenHelper.getUserName(request.getUserId());

        Order order = Order.builder()
                .userId(request.getUserId())
                .totalPrice(cart.getTotalValue())
                .subTotalPrice(cart.getSubtotal())
                .tax(cart.getTax())
                .userName(name)
                .products(cart.getItems())
                .paymentType(translatePaymentType(request.getPaymentTypeRequest()))
                .status(OrderStatus.PENDING.toString())
                .orderNumber(orderSequenceUseCase.execute())
                .createdDate(LocalDateTime.now(ZoneOffset.UTC))
                .isFinished(false)
                .build();

        orderMongoRepository.save(order);

        cartRepository.delete(cart);

        return order;
    }

    private PaymentType translatePaymentType(PaymentTypeRequest paymentTypeRequest) {
        if (paymentTypeRequest.getHomePaymentRequest() != null) {
            return PaymentType.builder()
                    .homePaymentRequest(HomePaymentRequest.valueOf(paymentTypeRequest.getHomePaymentRequest()))
                    .build();
        } else {
            return PaymentType.builder()
                    .cardPaymentRequest(paymentTypeRequest.getCardPaymentRequest())
                    .build();
        }
    }
}