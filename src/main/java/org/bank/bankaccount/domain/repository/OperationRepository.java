package org.bank.bankaccount.domain.repository;

import org.bank.bankaccount.domain.model.Account;
import org.bank.bankaccount.domain.model.Operation;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Interface to manage operation in database
 */
public interface OperationRepository {

    /**
     * save an Operation
     *
     * @param operation must not be null.
     * @return guaranteed to be not null.
     */
    Operation save(Operation operation);

    /**
     * get list operations by account
     *
     * @param account  must not be null.
     * @param pageable must not be null.
     * @return guaranteed to be not null.
     */
    List<Operation> findByAccount(Account account, Pageable pageable);
}
