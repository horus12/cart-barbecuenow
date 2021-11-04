package com.tcc.barbecuenow.cart.usecase.cart;

import com.tcc.barbecuenow.cart.controller.domain.request.cart.AddProductRequest;
import com.tcc.barbecuenow.cart.data.cart.CartRepository;
import com.tcc.barbecuenow.cart.domain.product.FinalProduct;
import com.tcc.barbecuenow.cart.domain.cart.Cart;
import com.tcc.barbecuenow.cart.domain.cart.CartItem;
import com.tcc.barbecuenow.cart.util.TokenHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AddProductUseCase {
    private final CartRepository cartRepository;
    private final TokenHelper tokenHelper;
    private final CreateCartUseCase createCartUseCase;
    private final GetProductInfoUseCase getProductInfoUseCase;

    public Cart execute(AddProductRequest addProductRequest) throws Exception {
        String email = tokenHelper.execute(addProductRequest.getIdToken());
        if (email == null) throw new Exception("invalid_token");

        Cart cart;
        cart = cartRepository.findByEmail(email);
        if (cart == null)
            cart = createCartUseCase.execute(addProductRequest.getIdToken());

        FinalProduct product = getProductInfoUseCase.execute(addProductRequest.getCartItem().getId());
        CartItem cartItem = addProductRequest.getCartItem();
        CartItem itemToAdd =  CartItem.builder().Id(addProductRequest.getCartItem().getId())
                .defaultImage(product.getProduct().getDefaultImage())
                .price(product.getPrice())
                .name(product.getProduct().getName())
                .Quantity(cartItem.getQuantity()).build();

        cart.addItemToCart(itemToAdd);

        cartRepository.save(cart);

        return cart;
    }
}
