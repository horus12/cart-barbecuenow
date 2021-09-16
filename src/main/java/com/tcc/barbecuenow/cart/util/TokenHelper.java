package com.tcc.barbecuenow.cart.util;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseToken;
import com.google.firebase.auth.UserRecord;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class TokenHelper {
    public String execute(String token) {
        UserRecord decodedToken;
        try {
            decodedToken = FirebaseAuth.getInstance().getUser(token);
        } catch (Exception e) {
            return null;
        }

        if (decodedToken != null) return decodedToken.getEmail();
        else return null;

    }
}
