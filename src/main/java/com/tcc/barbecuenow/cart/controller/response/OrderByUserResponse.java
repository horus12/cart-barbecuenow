package com.tcc.barbecuenow.cart.controller.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.tcc.barbecuenow.cart.domain.order.Order;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
@Data
public class OrderByUserResponse {

    private List<Order> inProgress;

    private List<Order> finished;
}

