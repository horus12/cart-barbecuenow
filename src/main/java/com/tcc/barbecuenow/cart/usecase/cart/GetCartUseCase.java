package com.tcc.barbecuenow.cart.usecase.cart;


import com.tcc.barbecuenow.cart.data.cart.CartRepository;
import com.tcc.barbecuenow.cart.domain.cart.Cart;
import com.tcc.barbecuenow.cart.usecase.cart.CreateCartUseCase;
import com.tcc.barbecuenow.cart.util.TokenHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class GetCartUseCase {
    private final CartRepository cartRepository;
    private final TokenHelper tokenHelper;
    private final CreateCartUseCase createCartUseCase;

    public Cart execute(String id) throws Exception {
        String email = tokenHelper.execute(id);
        if (email == null) throw new Exception("invalid_token");

        Cart cart;
        cart = cartRepository.findByEmail(email);
        if (cart == null)
            cart = createCartUseCase.execute(id);



        return cart;
    }
}
