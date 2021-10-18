package com.tcc.barbecuenow.cart.usecase.order;

import com.tcc.barbecuenow.cart.data.order.OrderMongoRepository;
import com.tcc.barbecuenow.cart.domain.order.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@RequiredArgsConstructor
@Service
public class GetOrderUseCase {

    private final OrderMongoRepository orderMongoRepository;

    public List<Order> execute(String status){
        return orderMongoRepository.findAllByStatus(status);
    }
}
