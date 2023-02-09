package org.bank.bankaccount.domain.service;

import org.bank.bankaccount.application.request.OperationRequest;
import org.bank.bankaccount.application.response.OperationHistoryResponse;
import org.bank.bankaccount.domain.exception.GenericException;
import org.bank.bankaccount.domain.model.Account;

/**
 * Service to manage Operations
 */
public interface OperationService {

    /**
     * execute an operation deposit or withdrawal
     *
     * @param operationRequest must not be null.
     * @return guaranteed to be not null.
     * @throws GenericException
     */
    String executeOperation(OperationRequest operationRequest) throws GenericException;

    /**
     * save an Operation
     *
     * @param amount  must not be null.
     * @param account must not be null.
     */
    void save(Double amount, Account account);

    /**
     * get all operations
     *
     * @param accountNumber must not be null.
     * @param page
     * @param size
     * @return guaranteed to be not null.
     * @throws GenericException
     */
    OperationHistoryResponse getAllOperations(Integer accountNumber, int page, int size) throws GenericException;
}
