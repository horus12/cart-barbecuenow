package com.tcc.barbecuenow.cart.usecase.order;

import com.tcc.barbecuenow.cart.data.order.OrderMongoRepository;
import com.tcc.barbecuenow.cart.domain.order.Order;
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
            String currentOrderStatus = _order.getStatus();

            switch (currentOrderStatus){
                case "pending":
                    _order.setStatus("preparing");
                    break;
                case "preparing":
                    _order.setStatus("delivery");
                    break;
                case "delivery":
                    _order.setStatus("finished");
                    break;
                case "finished":
                    throw new Exception("Order already in final status");
            }

            orderMongoRepository.save(_order);
        } else {
            throw new Exception("Order_not_found");
        }
    }
}
