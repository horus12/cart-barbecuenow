package com.tcc.barbecuenow.cart.usecase.order;

import com.tcc.barbecuenow.cart.data.order.OrderMongoRepository;
import com.tcc.barbecuenow.cart.domain.order.Order;
import com.tcc.barbecuenow.cart.domain.order.OrderStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ChangeStatusUseCase {
    private final OrderMongoRepository orderMongoRepository;

    public void execute(String orderId) throws Exception {
        Optional<Order> order = orderMongoRepository.findById(orderId);

        if(order.isPresent()){
            Order _order = order.get();
            OrderStatus currentOrderStatus =  OrderStatus.valueOf(_order.getStatus());

            if(currentOrderStatus.equals(OrderStatus.REJECTED)){
                throw new Exception("Status cannot be changed");
            }

            switch (currentOrderStatus){
                case PENDING:
                    _order.setStatus(OrderStatus.PREPARING.toString());
                    break;
                case PREPARING:
                    _order.setStatus(OrderStatus.DELIVERY.toString());
                    break;
                case DELIVERY:
                    _order.setStatus(OrderStatus.FINISHED.toString());
                    break;
                case FINISHED:
                    throw new Exception("Order already in final status");
            }

            orderMongoRepository.save(_order);
        } else {
            throw new Exception("Order not found");
        }
    }
}
