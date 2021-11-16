package com.tcc.barbecuenow.cart.controller.api;

import com.tcc.barbecuenow.cart.controller.domain.request.order.OrderRequest;
import com.tcc.barbecuenow.cart.controller.domain.request.order.RejectRequest;
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
    ResponseEntity<?> rejectOrder(@RequestBody RejectRequest rejectRequest, @PathVariable String orderId);

    @GetMapping(path = "/user/{userId}")
    ResponseEntity<?> getOrdersByUser(@PathVariable String userId);

    @GetMapping(path = "/user/{userId}/{orderNumber}")
    ResponseEntity<?> getOrderDetail(@PathVariable String userId,@PathVariable int orderNumber);
}
