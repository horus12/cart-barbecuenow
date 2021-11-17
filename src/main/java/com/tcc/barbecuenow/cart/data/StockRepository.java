package com.tcc.barbecuenow.cart.data;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.tcc.barbecuenow.cart.config.StockWebClient;
import com.tcc.barbecuenow.cart.controller.domain.request.stock.StockRemovalRequest;
import com.tcc.barbecuenow.cart.controller.response.StockRemovalResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class StockRepository {

    @Autowired
    private StockWebClient stockWebClient;

    public void removeItemsFromStock(StockRemovalRequest request) throws JsonProcessingException {

        ResponseEntity<?> response = stockWebClient
                .restTemplateStock()
                .postForEntity(
                        "http://localhost:8081/stock/removeStock", request, void.class
                );

        response.getBody();
    }
}
