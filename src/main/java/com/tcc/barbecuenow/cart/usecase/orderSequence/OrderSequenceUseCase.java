package com.tcc.barbecuenow.cart.usecase.orderSequence;

import com.tcc.barbecuenow.cart.data.order.sequence.SequenceMongoRepository;
import com.tcc.barbecuenow.cart.domain.orderSequence.Sequence;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class OrderSequenceUseCase {

    private final SequenceMongoRepository sequenceMongoRepository;

    public int execute() {
        Sequence sequence;
        try {
            sequence = sequenceMongoRepository.findAll().get(0);
        } catch (Exception e) {
            sequence = Sequence.builder().sequence(0).build();
        }
        sequence.setSequence(sequence.getSequence() + 1);

        sequenceMongoRepository.save(sequence);

        return sequence.getSequence();
    }
}
