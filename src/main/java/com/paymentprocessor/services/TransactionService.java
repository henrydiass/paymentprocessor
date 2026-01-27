package com.paymentprocessor.services;

import com.paymentprocessor.domain.entities.transaction.Transaction;
import com.paymentprocessor.domain.entities.user.User;
import com.paymentprocessor.dtos.TransactionDTO;
import com.paymentprocessor.repositories.TransactionRepository;
import com.paymentprocessor.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;

@Service
public class TransactionService {
    @Autowired
    RestTemplate restTemplate;

    @Autowired
    TransactionRepository repository;

    @Autowired
    NotificationService notificationService;

    @Autowired
    UserService userService;

    @Transactional(rollbackFor = Exception.class)
    public Transaction createTransaction(TransactionDTO dto) throws Exception {
        User sender = userService.getUserById(dto.senderId());
        User receiver = userService.getUserById(dto.receiverId());

        userService.validateTransaction(sender, receiver, dto.amount());

        boolean authorized = authorizeTransaction();

        if (!authorized) {
            throw new Exception("Transacao nao autorizada");
        }

        Transaction transaction = new Transaction();
        transaction.setAmount(dto.amount());
        transaction.setSender(sender);
        transaction.setReceiver(receiver);
        transaction.setDateTime(LocalDateTime.now());

        sender.setBalance(sender.getBalance().subtract(dto.amount()));
        receiver.setBalance(receiver.getBalance().add(dto.amount()));

        repository.save(transaction);
        userService.saveUser(sender);
        userService.saveUser(receiver);

        notificationService.sendNotification(sender.getName());
        notificationService.sendNotification(receiver.getName());

        return transaction;
    }


    private boolean authorizeTransaction() {
        ResponseEntity<Map> authResponse = restTemplate.getForEntity("https://util.devi.tools/api/v2/authorize", Map.class);

        if(authResponse.getStatusCode() == HttpStatus.OK) {
            String status = (String) authResponse.getBody().get("status");
            return "success".equalsIgnoreCase(status);
        } else return false;
    }
}
