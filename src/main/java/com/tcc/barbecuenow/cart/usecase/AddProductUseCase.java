package com.tcc.barbecuenow.cart.usecase;

import com.tcc.barbecuenow.cart.controller.request.AddProductRequest;
import com.tcc.barbecuenow.cart.data.CartRepository;
import com.tcc.barbecuenow.cart.domain.Cart;
import com.tcc.barbecuenow.cart.util.TokenHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class AddProductUseCase {
    private final CartRepository cartRepository;
    private final TokenHelper tokenHelper;

    public Cart execute(AddProductRequest addProductRequest) throws Exception {
        String email = tokenHelper.execute(addProductRequest.getIdToken());
        if (email==null) throw new Exception("invalid_token");

        Optional<Cart> cart = cartRepository.findById(addProductRequest.getCartId());

        if(cart.isEmpty()) throw new Exception("cart_not_found");

        cart.get().addItemToCart(addProductRequest.getCartItem());

        cartRepository.save(cart.get());

        return cart.get();
    }
}
