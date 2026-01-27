package com.paymentprocessor.services;

import com.paymentprocessor.domain.entities.user.User;
import com.paymentprocessor.domain.entities.user.UserType;
import com.paymentprocessor.dtos.UserDTO;
import com.paymentprocessor.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository repository;

    public void validateTransaction(User sender, User receiver, BigDecimal amount) throws Exception {
        if (sender.getType() == UserType.MERCHANT) {
            throw new Exception("Tipo de usuario nao autorizado a realizar transacoes");
        }

        if (sender.getBalance().compareTo(amount) < 0) {
            throw new Exception("Saldo insuficiente");
        }
    }

    public User getUserById(Long id) throws Exception {
        return repository.findById(id).orElseThrow(() -> new Exception("Usuario nao encontrado"));
    }

    public User saveUser(User user) {
        return repository.save(user);
    }

    public User createUser(UserDTO payload) {
        User user = new User(payload);
        return repository.save(user);
    }

    public List<User> getUsers() {
        return repository.findAll();
    }
}
