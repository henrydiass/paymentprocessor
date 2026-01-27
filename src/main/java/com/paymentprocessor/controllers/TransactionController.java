package com.paymentprocessor.controllers;

import com.paymentprocessor.domain.entities.transaction.Transaction;
import com.paymentprocessor.dtos.TransactionDTO;
import com.paymentprocessor.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TransactionController {
    @Autowired
    TransactionService service;

    @RequestMapping("/transfer")
    public ResponseEntity<Transaction> createTransaction(@RequestBody TransactionDTO dto) throws Exception {
        Transaction transaction = service.createTransaction(dto);
        return new ResponseEntity<>(transaction, HttpStatus.CREATED);
    }
}
