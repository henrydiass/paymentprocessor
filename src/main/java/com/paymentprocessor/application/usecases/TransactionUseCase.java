package com.paymentprocessor.application.usecases;

import com.paymentprocessor.domain.entities.transaction.Transaction;
import com.paymentprocessor.domain.services.AuthorizationService;
import com.paymentprocessor.domain.services.TransactionService;
import com.paymentprocessor.dtos.TransactionDTO;
import com.paymentprocessor.events.TransactionEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TransactionUseCase {

    @Autowired
    private  TransactionService service;
    @Autowired
    private  ApplicationEventPublisher publisher;
    @Autowired
    private AuthorizationService authService;

    @Transactional(rollbackFor = Exception.class)
    public Transaction createTransaction(TransactionDTO dto) throws Exception {
        boolean auth = authService.authorizeTransaction();
        if(!auth) throw new Exception("Transação não autorizada");

        Transaction transaction = service.execute(dto);
        publisher.publishEvent(new TransactionEvent(transaction.getSender(), transaction.getReceiver()));

        return transaction;

    }
}
