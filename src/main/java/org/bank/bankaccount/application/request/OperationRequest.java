package org.bank.bankaccount.application.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class OperationRequest {

    @NotNull @Min(1)
    private Integer accountNumber;

    @NotNull
    private Double amount;
}
