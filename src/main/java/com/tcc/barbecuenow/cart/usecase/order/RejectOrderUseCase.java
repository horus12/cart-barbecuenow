package com.tcc.barbecuenow.cart.usecase.order;

import com.tcc.barbecuenow.cart.controller.domain.request.order.RejectRequest;
import com.tcc.barbecuenow.cart.data.order.OrderMongoRepository;
import com.tcc.barbecuenow.cart.domain.order.Order;
import com.tcc.barbecuenow.cart.domain.order.OrderStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class RejectOrderUseCase {

    private final OrderMongoRepository orderMongoRepository;

    public void execute( RejectRequest rejectRequest, String orderId) throws Exception {

        Optional<Order> order = orderMongoRepository.findById(orderId);

        if(order.isPresent()){
            Order _order = order.get();
            OrderStatus currentOrderStatus =  OrderStatus.valueOf(_order.getStatus());

            if(currentOrderStatus.equals(OrderStatus.REJECTED)){
                throw new Exception("Status is already rejected");
            }

            _order.setStatus(OrderStatus.REJECTED.toString());
            _order.setRejectJustification(rejectRequest.getRejectJustification());

            orderMongoRepository.save(_order);
        } else {
            throw new Exception("Order not found");
        }
    }
}
