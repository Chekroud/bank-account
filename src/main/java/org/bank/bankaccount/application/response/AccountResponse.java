package org.bank.bankaccount.application.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AccountResponse {
    
    private Integer accountNumber;
    private String token;
}
