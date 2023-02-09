package org.bank.bankaccount.domain.repository;

import org.bank.bankaccount.domain.model.Account;

import java.util.Optional;

/**
 * Interface to manage account in database
 */
public interface AccountRepository {

    /**
     * get Account by account number
     *
     * @param accountNumber must not be null.
     * @return guaranteed to be not null.
     */
    Optional<Account> findByAccountNumber(Integer accountNumber);

    /**
     * get Account by account number and username client
     *
     * @param accountNumber must not be null.
     * @param username      must not be null.
     * @return guaranteed to be not null.
     */
    Optional<Account> findByAccountNumberAndUsername(Integer accountNumber, String username);

    /**
     * save an Account
     *
     * @param account must not be null.
     * @return guaranteed to be not null.
     */
    Account save(Account account);
}
