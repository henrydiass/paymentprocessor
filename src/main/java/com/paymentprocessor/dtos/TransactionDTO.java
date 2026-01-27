package com.paymentprocessor.dtos;

import com.paymentprocessor.domain.entities.user.User;

import java.math.BigDecimal;

public record TransactionDTO(BigDecimal amount, Long senderId, Long receiverId) {}
