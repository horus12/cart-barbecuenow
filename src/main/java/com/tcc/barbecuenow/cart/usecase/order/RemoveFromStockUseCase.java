package com.tcc.barbecuenow.cart.usecase.order;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.tcc.barbecuenow.cart.controller.domain.request.stock.StockRemovalRequest;
import com.tcc.barbecuenow.cart.data.StockRepository;
import com.tcc.barbecuenow.cart.domain.cart.CartItem;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class RemoveFromStockUseCase {

    private final StockRepository stockRepository;

    public void execute(List<CartItem> items) throws JsonProcessingException {

        stockRepository.removeItemsFromStock(StockRemovalRequest.builder().items(items).build());
    }
}
