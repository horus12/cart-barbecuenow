package com.tcc.barbecuenow.cart.usecase.cart;

import com.tcc.barbecuenow.cart.controller.domain.request.cart.AddProductRequest;
import com.tcc.barbecuenow.cart.data.cart.CartRepository;
import com.tcc.barbecuenow.cart.domain.cart.Cart;
import com.tcc.barbecuenow.cart.util.TokenHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class AddProductUseCase {
    private final CartRepository cartRepository;
    private final TokenHelper tokenHelper;
    private final CreateCartUseCase createCartUseCase;

    public Cart execute(AddProductRequest addProductRequest) throws Exception {
        String email = tokenHelper.execute(addProductRequest.getIdToken());
        if (email == null) throw new Exception("invalid_token");

        Optional<Cart> cart;
        if (addProductRequest.getCartId() == null)
            cart = Optional.ofNullable(createCartUseCase.execute(addProductRequest.getIdToken()));
        else
            cart = cartRepository.findById(addProductRequest.getCartId());

        if (cart.isEmpty()) throw new Exception("cart_not_found");

        Cart cartNormalized = cart.get();

        cartNormalized.addItemToCart(addProductRequest.getCartItem());

        cartRepository.save(cart.get());

        return cart.get();
    }
}
