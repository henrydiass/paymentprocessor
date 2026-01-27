package com.paymentprocessor.domain.entities.user;

import com.paymentprocessor.dtos.UserDTO;
import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;

@Entity(name = "users")
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(unique = true)
    private String document;

    @Column(unique = true)
    private String email;

    private String password;

    private BigDecimal balance;

    @Enumerated(EnumType.STRING)
    private UserType type;

    public User(UserDTO dto) {
        this.name = dto.name();
        this.document = dto.document();
        this.email = dto.email();
        this.password = dto.password();
        this.type = dto.type();
        this.balance = dto.balance();
    }
}
