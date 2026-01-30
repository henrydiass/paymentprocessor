package com.paymentprocessor.events.listeners;

import com.paymentprocessor.events.TransactionEvent;
import com.paymentprocessor.domain.services.NotificationService;
import com.paymentprocessor.domain.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
public class TransactionListener {
    @Autowired
    private NotificationService notificationService;
    @Autowired
    private UserService userService;
    
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    @Async
    public void onTransactionNotificate(TransactionEvent event) {
        notificationService.sendNotification(event.getSender().getName());
        notificationService.sendNotification(event.getReceiver().getName());
    }

    @TransactionalEventListener(phase = TransactionPhase.BEFORE_COMMIT)
    public void syncUserTransaction(TransactionEvent event) {
        System.out.println("Salvando usuarios");
        userService.saveUser(event.getSender());
        userService.saveUser(event.getReceiver());
    }
}
