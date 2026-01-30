package com.paymentprocessor.domain.services;

import com.paymentprocessor.domain.entities.transaction.Transaction;
import com.paymentprocessor.domain.entities.user.User;
import com.paymentprocessor.dtos.TransactionDTO;
import com.paymentprocessor.events.TransactionEvent;
import com.paymentprocessor.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.Map;

@Service
public class TransactionService {
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private TransactionRepository repository;

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private UserService userService;

    @Autowired
    private ApplicationEventPublisher publisher;


    public Transaction execute(TransactionDTO dto) throws Exception {
        User sender = userService.getUserById(dto.senderId());
        User receiver = userService.getUserById(dto.receiverId());

        userService.validateTransaction(sender, receiver, dto.amount());

        Transaction transaction = new Transaction();
        transaction.setAmount(dto.amount());
        transaction.setSender(sender);
        transaction.setReceiver(receiver);
        transaction.setDateTime(LocalDateTime.now());

        repository.save(transaction);

        sender.setBalance(sender.getBalance().subtract(dto.amount()));
        receiver.setBalance(receiver.getBalance().add(dto.amount()));

        return transaction;
    }
}
