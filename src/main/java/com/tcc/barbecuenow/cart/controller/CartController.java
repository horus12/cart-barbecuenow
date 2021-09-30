package com.tcc.barbecuenow.cart.controller;

import com.tcc.barbecuenow.cart.controller.request.AddProductRequest;
import com.tcc.barbecuenow.cart.controller.request.CreateCartRequest;
import com.tcc.barbecuenow.cart.controller.request.DeleteProductRequest;
import com.tcc.barbecuenow.cart.controller.request.UpdateProductRequest;
import com.tcc.barbecuenow.cart.domain.Cart;
import com.tcc.barbecuenow.cart.usecase.AddProductUseCase;
import com.tcc.barbecuenow.cart.usecase.CreateCartUseCase;
import com.tcc.barbecuenow.cart.usecase.DeleteProductUseCase;
import com.tcc.barbecuenow.cart.usecase.UpdateProductUseCase;
import com.tcc.barbecuenow.cart.util.ErrorHandler;
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
    private final DeleteProductUseCase deleteProductUseCase;
    private final UpdateProductUseCase updateProductUseCase;
    private final ErrorHandler errorHandler;

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
}
