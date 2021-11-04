package com.tcc.barbecuenow.cart.data;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.tcc.barbecuenow.cart.config.CatalogWebClient;
import com.tcc.barbecuenow.cart.domain.product.FinalProduct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CatalogRepository {

    @Autowired
    private CatalogWebClient catalogWebClient;

    public FinalProduct[] getProduct() throws JsonProcessingException {

        return catalogWebClient
                .restTemplate()
                .getForObject(
                        "http://localhost:8081/catalog/getProducts",
                        FinalProduct[].class);
    }
}
