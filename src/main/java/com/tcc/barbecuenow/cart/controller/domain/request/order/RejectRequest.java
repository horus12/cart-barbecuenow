package com.tcc.barbecuenow.cart.controller.domain.request.order;

import lombok.*;

@Data
@NoArgsConstructor
public class RejectRequest {
    @NonNull
    private String rejectJustification;
}
