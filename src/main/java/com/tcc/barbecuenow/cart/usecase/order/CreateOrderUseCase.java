package com.tcc.barbecuenow.cart.usecase.order;

import com.tcc.barbecuenow.cart.controller.domain.request.order.OrderRequest;
import com.tcc.barbecuenow.cart.data.order.OrderMongoRepository;
import com.tcc.barbecuenow.cart.domain.order.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CreateOrderUseCase {

    private final OrderMongoRepository orderMongoRepository;

    public Order execute(OrderRequest request) throws Exception{

        Order order = Order.builder().userId(request.getUserId())
                .totalPrice(request.getTotalPrice())
                .totalTax(request.getTotalTax())
                .products(request.getProducts())
                .address(request.getAddress())
                .status(request.getStatus())
                .build();

        orderMongoRepository.save(order);

        return order;
    }
}