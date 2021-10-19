package com.tcc.barbecuenow.cart.usecase.order;

import com.tcc.barbecuenow.cart.controller.domain.request.order.RejectRequest;
import com.tcc.barbecuenow.cart.data.order.OrderMongoRepository;
import com.tcc.barbecuenow.cart.domain.order.Order;
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
            String currentOrderStatus = _order.getStatus();

            if(currentOrderStatus.equals("rejected")){
                throw new Exception("Status is already rejected");
            }

            _order.setStatus("rejected");
            _order.setRejectJustification(rejectRequest.getRejectJustification());

            orderMongoRepository.save(_order);
        } else {
            throw new Exception("Order not found");
        }
    }
}
