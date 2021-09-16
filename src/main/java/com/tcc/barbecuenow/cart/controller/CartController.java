package com.tcc.barbecuenow.cart.controller;

import com.tcc.barbecuenow.cart.controller.request.AddProductRequest;
import com.tcc.barbecuenow.cart.controller.request.CreateCartRequest;
import com.tcc.barbecuenow.cart.domain.Cart;
import com.tcc.barbecuenow.cart.usecase.AddProductUseCase;
import com.tcc.barbecuenow.cart.usecase.CreateCartUseCase;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Data
@CrossOrigin
@RequiredArgsConstructor
public class CartController implements CartApi {
    private final CreateCartUseCase createCartUseCase;
    private final AddProductUseCase addProductUseCase;

    @Override
    public ResponseEntity<?> createCategory(CreateCartRequest createCartRequest) {
        Cart cart;
        try {
            cart = createCartUseCase.execute(createCartRequest.getIdToken());
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        if (cart != null)
            return new ResponseEntity<>(cart, HttpStatus.OK);
        else return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<?> addProduct(AddProductRequest addProductRequest) {
        Cart cart;
        try {
            cart = addProductUseCase.execute(addProductRequest);
        } catch (Exception e) {

            if (e.getMessage().equals("invalid_token")) return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

            if (e.getMessage().equals("cart_not_found")) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (cart != null)
            return new ResponseEntity<>(cart, HttpStatus.OK);
        else return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
