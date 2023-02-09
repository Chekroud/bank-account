package org.bank.bankaccount.infrastracture.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bank.bankaccount.domain.model.Account;
import org.hibernate.annotations.ColumnDefault;

@Data
@Entity(name = "account")
@NoArgsConstructor
@AllArgsConstructor
public class AccountEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_id")
    private Long accountId;

    @Column(name = "account_number", unique = true, nullable = false)
    private Integer accountNumber;

    @Column(name = "account_position", nullable = false)
    @ColumnDefault("0")
    private Double accountPosition;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private ClientEntity client;

    public Account toDomain() {
        return Account.builder()
                .accountId(accountId)
                .accountNumber(accountNumber)
                .accountPosition(accountPosition)
                .client(client.toDomain())
                .build();
    }
}
