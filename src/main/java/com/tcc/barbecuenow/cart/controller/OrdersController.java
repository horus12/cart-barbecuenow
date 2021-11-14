package com.tcc.barbecuenow.cart.controller;

import com.tcc.barbecuenow.cart.controller.api.OrdersApi;
import com.tcc.barbecuenow.cart.controller.domain.request.order.OrderRequest;
import com.tcc.barbecuenow.cart.controller.domain.request.order.RejectRequest;
import com.tcc.barbecuenow.cart.controller.response.OrderByUserResponse;
import com.tcc.barbecuenow.cart.usecase.order.*;
import com.tcc.barbecuenow.cart.domain.order.Order;
import com.tcc.barbecuenow.cart.util.ErrorHandler;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Data
@CrossOrigin
@RequiredArgsConstructor
public class OrdersController implements OrdersApi {

    private final GetOrderUseCase getOrdersUseCase;
    private final CreateOrderUseCase createOrderUseCase;
    private final ChangeStatusUseCase changeOrderStatusUseCase;
    private final RejectOrderUseCase rejectOrderUseCase;
    private final GetOrderByUserUseCase getOrderByUserUseCase;
    private final ErrorHandler errorHandler;


    @Override
    public ResponseEntity<?> getOrders(String status) {

        List<Order> ordersList = getOrdersUseCase.execute(status);

        return new ResponseEntity<>(ordersList, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> createOrders(OrderRequest orderRequest) {
        Order order;
        try {
            order = createOrderUseCase.execute(orderRequest);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return errorHandler.execute(e);
        }
        if (order != null)
            return new ResponseEntity<>(HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<?> changeStatus(String orderId) {
        try {
            changeOrderStatusUseCase.execute(orderId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            if (e.getMessage().equals("Order_not_found"))
                return new ResponseEntity<>("", HttpStatus.BAD_REQUEST);
            else
                return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<?> rejectOrder(RejectRequest rejectRequest, String orderId) {
        try {
            rejectOrderUseCase.execute(rejectRequest, orderId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<?> getOrdersByUser(String userId) {
        OrderByUserResponse response;
        try {
            response = getOrderByUserUseCase.execute(userId);
        } catch (Exception e) {
            return errorHandler.execute(e);
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}