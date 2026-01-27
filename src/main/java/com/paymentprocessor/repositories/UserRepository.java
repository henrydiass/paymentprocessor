package com.paymentprocessor.repositories;

import com.paymentprocessor.domain.entities.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
