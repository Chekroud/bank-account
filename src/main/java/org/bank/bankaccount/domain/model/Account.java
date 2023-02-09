package org.bank.bankaccount.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Account {

    @JsonIgnore
    private Long accountId;
    private Integer accountNumber;
    private Double accountPosition;
    private Client client;
}
