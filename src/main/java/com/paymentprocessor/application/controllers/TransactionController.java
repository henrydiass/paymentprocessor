package com.paymentprocessor.application.controllers;

import com.paymentprocessor.application.usecases.TransactionUseCase;
import com.paymentprocessor.domain.entities.transaction.Transaction;
import com.paymentprocessor.dtos.TransactionDTO;
import com.paymentprocessor.domain.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TransactionController {
    @Autowired
    TransactionUseCase transactionUseCase;

    @RequestMapping("/transfer")
    public ResponseEntity<Transaction> createTransaction(@RequestBody TransactionDTO dto) throws Exception {
        Transaction transaction = transactionUseCase.createTransaction(dto);
        return new ResponseEntity<>(transaction, HttpStatus.CREATED);
    }
}
