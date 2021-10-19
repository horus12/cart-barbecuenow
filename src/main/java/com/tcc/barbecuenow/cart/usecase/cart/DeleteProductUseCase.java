package com.tcc.barbecuenow.cart.usecase.cart;

import com.tcc.barbecuenow.cart.controller.domain.request.cart.DeleteProductRequest;
import com.tcc.barbecuenow.cart.data.cart.CartRepository;
import com.tcc.barbecuenow.cart.domain.cart.Cart;
import com.tcc.barbecuenow.cart.util.TokenHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class DeleteProductUseCase {
    private final CartRepository cartRepository;
    private final TokenHelper tokenHelper;


    public Cart execute(DeleteProductRequest request) throws Exception {
        String email = tokenHelper.execute(request.getIdToken());
        if (email == null) throw new Exception("invalid_token");

        Optional<Cart> cart = cartRepository.findById(request.getCartId());

        if (cart.isEmpty()) throw new Exception("cart_not_found");

        Cart cartFromRepository = cart.get();
        cartFromRepository.removeProductFromCart(request.getItemId());
        cartRepository.save(cartFromRepository);

        return cartFromRepository;
    }
}
