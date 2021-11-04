package com.tcc.barbecuenow.cart.domain.Tax;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "tax")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Tax {

    @Id
    String id;

    double value;
}
