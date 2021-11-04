package com.tcc.barbecuenow.cart.controller.domain.request.cart;

import com.tcc.barbecuenow.cart.domain.cart.CartItem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateProductRequest {

    @NonNull
    String idToken;

    @NonNull
    CartItem cartItem;
}
