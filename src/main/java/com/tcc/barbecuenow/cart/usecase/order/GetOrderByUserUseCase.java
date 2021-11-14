package com.tcc.barbecuenow.cart.usecase.order;

import com.tcc.barbecuenow.cart.controller.response.OrderByUserResponse;
import com.tcc.barbecuenow.cart.data.order.OrderMongoRepository;
import com.tcc.barbecuenow.cart.domain.order.Order;
import com.tcc.barbecuenow.cart.util.TokenHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class GetOrderByUserUseCase {

    private final OrderMongoRepository orderMongoRepository;
    private final TokenHelper tokenHelper;

    public OrderByUserResponse execute(String userId) throws Exception {
        String email = tokenHelper.execute(userId);
        if (email == null) throw new Exception("invalid_token");

        List<Order> orders = orderMongoRepository.findAllByUserId(userId);

        List<Order> inProgressOrders = orders.stream()
                .filter(s -> !s.getIsFinished()).collect(Collectors.toList());

        List<Order> finishedOrders = orders.stream()
                .filter(Order::getIsFinished).collect(Collectors.toList());

        return OrderByUserResponse.builder()
                .inProgress(!inProgressOrders.isEmpty() ? inProgressOrders : Collections.emptyList())
                .finished(!finishedOrders.isEmpty() ? finishedOrders : Collections.emptyList())
                .build();
    }
}
