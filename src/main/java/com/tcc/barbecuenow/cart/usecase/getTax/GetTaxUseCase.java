package com.tcc.barbecuenow.cart.usecase.getTax;

import com.tcc.barbecuenow.cart.data.tax.TaxRepository;
import com.tcc.barbecuenow.cart.domain.Tax.Tax;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class GetTaxUseCase {

    private final TaxRepository taxRepository;

    public double execute() {
        Tax tax = taxRepository.findAll().get(0);
        return tax.getValue();
    }
}
