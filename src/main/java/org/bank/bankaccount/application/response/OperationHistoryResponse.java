package org.bank.bankaccount.application.response;

import org.bank.bankaccount.domain.model.Operation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OperationHistoryResponse {
    private List<Operation> operations;
    private int size;

    public OperationHistoryResponse(List<Operation> operations) {
        this.operations = operations;
        size = operations.size();
    }
}
