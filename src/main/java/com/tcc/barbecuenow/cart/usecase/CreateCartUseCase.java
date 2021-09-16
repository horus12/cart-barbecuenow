package com.tcc.barbecuenow.cart.usecase;

import com.tcc.barbecuenow.cart.data.CartRepository;
import com.tcc.barbecuenow.cart.domain.Cart;
import com.tcc.barbecuenow.cart.util.TokenHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collections;

@RequiredArgsConstructor
@Service
public class CreateCartUseCase {
    private final CartRepository cartRepository;
    private final TokenHelper tokenHelper;

    public Cart execute(String token) throws Exception {
        String email = tokenHelper.execute(token);
        if (email==null) throw new Exception("invalid_token");

        Cart cart = Cart.builder()
                .email(email)
                .items(Collections.emptyList())
                .totalValue(0)
                .createdDate(LocalDate.now())
                .build();
        cartRepository.save(cart);

        return cart;
    }
}
