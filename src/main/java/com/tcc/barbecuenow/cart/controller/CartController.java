package com.tcc.barbecuenow.cart.controller;

import com.tcc.barbecuenow.cart.controller.api.CartApi;
import com.tcc.barbecuenow.cart.controller.domain.request.cart.AddProductRequest;
import com.tcc.barbecuenow.cart.controller.domain.request.cart.CreateCartRequest;
import com.tcc.barbecuenow.cart.controller.domain.request.cart.DeleteProductRequest;
import com.tcc.barbecuenow.cart.controller.domain.request.cart.UpdateProductRequest;

import com.tcc.barbecuenow.cart.domain.cart.Cart;
import com.tcc.barbecuenow.cart.usecase.cart.GetCartUseCase;
import com.tcc.barbecuenow.cart.usecase.cart.AddProductUseCase;
import com.tcc.barbecuenow.cart.usecase.cart.CreateCartUseCase;
import com.tcc.barbecuenow.cart.usecase.cart.DeleteProductUseCase;
import com.tcc.barbecuenow.cart.usecase.cart.UpdateProductUseCase;
import com.tcc.barbecuenow.cart.util.ErrorHandler;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Data
@CrossOrigin
@RequiredArgsConstructor
public class CartController implements CartApi {
    private final CreateCartUseCase createCartUseCase;
    private final AddProductUseCase addProductUseCase;
    private final DeleteProductUseCase deleteProductUseCase;
    private final UpdateProductUseCase updateProductUseCase;
    private final GetCartUseCase getCartUseCase;
    private final ErrorHandler errorHandler;

    @Override
    public ResponseEntity<?> createCart(CreateCartRequest createCartRequest) {
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
            return errorHandler.execute(e);
        }

        if (cart != null)
            return new ResponseEntity<>(cart, HttpStatus.OK);
        else return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<?> deleteProduct(DeleteProductRequest deleteProductRequest) {
        Cart cart;
        try {
            cart = deleteProductUseCase.execute(deleteProductRequest);
        } catch (Exception e) {
            return errorHandler.execute(e);
        }
        if (cart != null)
            return new ResponseEntity<>(cart, HttpStatus.OK);
        else return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<?> updateProduct(UpdateProductRequest updateProductRequest) {
        Cart cart;
        try {
            cart = updateProductUseCase.execute(updateProductRequest);
        } catch (Exception e) {
            return errorHandler.execute(e);
        }

        if (cart != null)
            return new ResponseEntity<>(cart, HttpStatus.OK);
        else return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<?> getCart(String userId) {
        Cart cart;
        try {
            cart = getCartUseCase.execute(userId);
        } catch (Exception e) {
            return errorHandler.execute(e);
        }

        if (cart != null)
            return new ResponseEntity<>(cart, HttpStatus.OK);
        else return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
