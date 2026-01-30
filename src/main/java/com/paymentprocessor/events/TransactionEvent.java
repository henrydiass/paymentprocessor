package com.paymentprocessor.events;

import com.paymentprocessor.domain.entities.user.User;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class TransactionEvent {
    private User sender;
    private User receiver;
    private BigDecimal amount;

    public TransactionEvent(User sender, User receiver) {
        this.receiver = receiver;
        this.sender = sender;
    }
}
