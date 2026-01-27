package com.paymentprocessor.dtos;

import java.math.BigDecimal;

public record TransactionResponseDTO(Long id, BigDecimal amount) {
}
