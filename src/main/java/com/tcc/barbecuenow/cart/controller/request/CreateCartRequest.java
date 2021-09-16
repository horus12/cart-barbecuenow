package com.tcc.barbecuenow.cart.controller.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCartRequest {

    @NonNull
    String idToken;
}
