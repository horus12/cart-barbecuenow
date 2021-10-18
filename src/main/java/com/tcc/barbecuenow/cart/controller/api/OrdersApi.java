package com.tcc.barbecuenow.cart.controller.api;

import com.tcc.barbecuenow.cart.controller.domain.request.order.OrderRequest;
import com.tcc.barbecuenow.cart.controller.domain.request.order.RejectOrderRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RequestMapping ("/orders")
public interface OrdersApi {

    @GetMapping(path = "/{status}")
    ResponseEntity<?> getOrders(@PathVariable String status);

    @PostMapping("/createOrder")
    ResponseEntity<?> createOrders(@RequestBody OrderRequest orderRequest);

    @PutMapping("/changeStatus/{orderId}")
    ResponseEntity<?> changeStatus(@PathVariable String orderId);

    @PutMapping("/rejectOrder/{orderId}")
    ResponseEntity<?> rejectOrder(@RequestBody RejectOrderRequest rejectOrderRequest, @PathVariable String orderId);
}
