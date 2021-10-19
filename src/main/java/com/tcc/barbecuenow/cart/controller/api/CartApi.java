package com.tcc.barbecuenow.cart.controller.api;

import com.tcc.barbecuenow.cart.controller.domain.request.cart.AddProductRequest;
import com.tcc.barbecuenow.cart.controller.domain.request.cart.CreateCartRequest;
import com.tcc.barbecuenow.cart.controller.domain.request.cart.DeleteProductRequest;
import com.tcc.barbecuenow.cart.controller.domain.request.cart.UpdateProductRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RequestMapping("cart")
public interface CartApi {

    @PostMapping("/createCart")
    ResponseEntity<?> createCategory(@RequestBody CreateCartRequest createCartRequest);

    @PostMapping("/addProduct")
    ResponseEntity<?> addProduct(@RequestBody AddProductRequest addProductRequest);

    @DeleteMapping("/deleteProduct")
    ResponseEntity<?> deleteProduct(@RequestBody DeleteProductRequest deleteProductRequest);

    @PostMapping("/updateProduct")
    ResponseEntity<?> updateProduct(@RequestBody UpdateProductRequest updateProductRequest);
}
