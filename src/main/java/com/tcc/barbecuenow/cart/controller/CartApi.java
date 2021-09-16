package com.tcc.barbecuenow.cart.controller;

import com.tcc.barbecuenow.cart.controller.request.AddProductRequest;
import com.tcc.barbecuenow.cart.controller.request.CreateCartRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@CrossOrigin
@RequestMapping("cart")
public interface CartApi {

    @PostMapping("/createCart")
    ResponseEntity<?> createCategory(@RequestBody CreateCartRequest createCartRequest);

    @PostMapping("/addProduct")
    ResponseEntity<?> addProduct(@RequestBody AddProductRequest addProductRequest);
}
