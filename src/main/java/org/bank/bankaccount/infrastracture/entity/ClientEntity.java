package org.bank.bankaccount.infrastracture.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bank.bankaccount.domain.model.Client;
import org.bank.bankaccount.infrastracture.security.RoleNameEnum;

@Data
@Entity(name = "client")
@NoArgsConstructor
@AllArgsConstructor
public class ClientEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "client_id")
    private Long clientId;

    @Column(name = "client_number", unique = true, nullable = false)
    private Integer clientNumber;

    @Column(name = "username", unique = true, nullable = false)
    private String username;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private RoleNameEnum roleName;

    public Client toDomain() {
        return Client.builder()
                .clientId(clientId)
                .clientNumber(clientNumber)
                .username(username)
                .firstName(firstName)
                .lastName(lastName)
                .email(email)
                .roleName(roleName)
                .build();
    }
}
