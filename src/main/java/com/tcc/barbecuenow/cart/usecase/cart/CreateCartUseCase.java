package com.tcc.barbecuenow.cart.usecase.cart;

import com.tcc.barbecuenow.cart.data.cart.CartRepository;
import com.tcc.barbecuenow.cart.domain.cart.Cart;
import com.tcc.barbecuenow.cart.usecase.getTax.GetTaxUseCase;
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
    private final GetTaxUseCase getTaxUseCase;

    public Cart execute(String token) throws Exception {
        String email = tokenHelper.execute(token);
        if (email==null) throw new Exception("invalid_token");

        double tax = getTaxUseCase.execute();
        Cart cart = Cart.builder()
                .email(email)
                .items(Collections.emptyList())
                .totalValue(0)
                .subtotal(0)
                .tax(tax)
                .createdDate(LocalDate.now())
                .build();
        cartRepository.save(cart);

        return cart;
    }
}
