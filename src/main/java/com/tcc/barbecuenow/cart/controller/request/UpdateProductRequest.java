package com.tcc.barbecuenow.cart.controller.request;

import com.tcc.barbecuenow.cart.domain.CartItem;
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
    String cartId;

    @NonNull
    CartItem cartItem;
}
