package com.tcc.barbecuenow.cart.usecase.order;

import com.tcc.barbecuenow.cart.controller.domain.request.order.OrderRequest;
import com.tcc.barbecuenow.cart.data.order.OrderMongoRepository;
import com.tcc.barbecuenow.cart.domain.order.Order;
import com.tcc.barbecuenow.cart.domain.order.OrderStatus;
import com.tcc.barbecuenow.cart.usecase.orderSequence.OrderSequenceUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class CreateOrderUseCase {

    private final OrderMongoRepository orderMongoRepository;
    private final OrderSequenceUseCase orderSequenceUseCase;

    public Order execute(OrderRequest request) throws Exception{

        Order order = Order.builder().userId(request.getUserId())
                .totalPrice(request.getTotalPrice())
                .totalTax(request.getTotalTax())
                .products(request.getProducts())
                .address(request.getAddress())
                .status(OrderStatus.PENDING.toString())
                .orderNumber(orderSequenceUseCase.execute())
                .createdDate(LocalDateTime.now())
                .build();

        orderMongoRepository.save(order);

        return order;
    }
}