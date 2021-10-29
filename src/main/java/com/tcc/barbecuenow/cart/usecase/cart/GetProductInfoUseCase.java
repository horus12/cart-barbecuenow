package com.tcc.barbecuenow.cart.usecase.cart;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.tcc.barbecuenow.cart.data.CatalogRepository;
import com.tcc.barbecuenow.cart.domain.FinalProduct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class GetProductInfoUseCase {

    private final CatalogRepository catalogRepository;

    public FinalProduct execute(String id) throws JsonProcessingException {

        List<FinalProduct>  productList = Arrays.asList(catalogRepository.getProduct());

        Optional<FinalProduct> fProduct = productList.stream().
                filter(p -> p.getProduct().getId().equals(id)).
                findFirst();

        return fProduct.orElse(null);
    }
}
