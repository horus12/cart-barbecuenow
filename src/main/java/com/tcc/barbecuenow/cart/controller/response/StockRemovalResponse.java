package com.tcc.barbecuenow.cart.controller.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.tcc.barbecuenow.cart.domain.cart.CartItem;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
public class StockRemovalResponse {
    List<CartItem> items;

}
