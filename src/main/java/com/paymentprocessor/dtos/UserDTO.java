package com.paymentprocessor.dtos;

import com.paymentprocessor.domain.entities.user.UserType;

import java.math.BigDecimal;

public record UserDTO(String name, String document, String email, String password, BigDecimal balance, UserType type) {}
