package com.tcc.barbecuenow.cart.util;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ErrorHandler {
    public ResponseEntity<?> execute(Exception e) {

        if (e.getMessage() == null) return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

        if (e.getMessage().equals("invalid_token")) return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

        if (e.getMessage().equals("cart_not_found")) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        if (e.getMessage().equals("card_rejected")) return new ResponseEntity<>(HttpStatus.PAYMENT_REQUIRED);

        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
