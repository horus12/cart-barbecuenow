package com.tcc.barbecuenow.cart.domain.orderSequence;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "ordersSequence")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Sequence {

    @Id
    private String id;

    private int sequence;
}
