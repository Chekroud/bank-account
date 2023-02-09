package org.bank.bankaccount.infrastracture.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bank.bankaccount.domain.model.Operation;

import java.util.Date;

@Builder
@Data
@Entity(name = "operation")
@NoArgsConstructor
@AllArgsConstructor
public class OperationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "operation_id")
    private Long operationId;

    @Column(name = "type")
    private String type;

    @Column(name = "amount")
    private Double amount;

    @Column(name = "date")
    private Date date;

    @Column(name = "balance")
    private Double balance;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private AccountEntity account;

    public Operation toDomain() {
        return Operation.builder()
                .operationId(operationId)
                .type(type)
                .amount(amount)
                .date(date)
                .balance(balance)
                .account(account.toDomain())
                .build();
    }
}
