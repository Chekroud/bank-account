package org.bank.bankaccount.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Operation {

    @JsonIgnore
    private Long operationId;
    private String type;
    private Double amount;
    private Date date;
    private Double balance;
    @JsonIgnore
    private Account account;
}
