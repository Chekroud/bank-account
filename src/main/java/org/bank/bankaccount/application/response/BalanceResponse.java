package org.bank.bankaccount.application.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BalanceResponse {

    private Integer accountNumber;
    private Double accountBalance;
}
