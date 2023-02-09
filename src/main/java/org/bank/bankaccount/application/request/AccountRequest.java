package org.bank.bankaccount.application.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class AccountRequest {
    @NotNull
    @NotBlank
    private String username;
    private String email;
    private String firstName;
    private String lastName;
}
