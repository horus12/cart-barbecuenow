package com.tcc.barbecuenow.cart.controller.domain.request.cart;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeleteProductRequest {

    @NonNull
    String idToken;

    @NonNull
    String cartId;

    @NonNull
    String ItemId;
}
