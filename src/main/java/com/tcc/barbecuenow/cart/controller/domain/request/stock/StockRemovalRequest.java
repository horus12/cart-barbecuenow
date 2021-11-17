package com.tcc.barbecuenow.cart.controller.domain.request.stock;

import com.tcc.barbecuenow.cart.domain.cart.CartItem;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class StockRemovalRequest {

    List<CartItem> items;
}
