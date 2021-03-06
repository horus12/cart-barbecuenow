package com.tcc.barbecuenow.cart.usecase.cart;

import com.tcc.barbecuenow.cart.controller.domain.request.cart.UpdateProductRequest;
import com.tcc.barbecuenow.cart.data.cart.CartRepository;
import com.tcc.barbecuenow.cart.domain.cart.Cart;
import com.tcc.barbecuenow.cart.util.TokenHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UpdateProductUseCase {
    private final CartRepository cartRepository;
    private final TokenHelper tokenHelper;

    public Cart execute(UpdateProductRequest updateProductRequest) throws Exception {
        String email = tokenHelper.execute(updateProductRequest.getIdToken());
        if (email == null) throw new Exception("invalid_token");

        Cart cart;
        cart = cartRepository.findByEmail(email);

        if (cart == null) throw new Exception("cart_not_found");

        cart.updateProductQuantity(updateProductRequest.getCartItem());

        cartRepository.save(cart);

        return cart;
    }
}