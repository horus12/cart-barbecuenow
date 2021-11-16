package com.tcc.barbecuenow.cart.usecase.order;

import com.tcc.barbecuenow.cart.data.order.OrderMongoRepository;
import com.tcc.barbecuenow.cart.domain.order.Order;
import com.tcc.barbecuenow.cart.util.TokenHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class GetOrderDetailUseCase {
    private final OrderMongoRepository orderMongoRepository;
    private final TokenHelper tokenHelper;

    public Order execute(String userId, int orderNumber) throws Exception {
        String email = tokenHelper.execute(userId);
        if (email == null) throw new Exception("invalid_token");

        Order order = orderMongoRepository.findOrderByUserIdAndOrderNumber(userId, orderNumber);
        if (order == null) throw new Exception("order_not_found");

        return order;
    }
}