package org.bank.bankaccount.domain.service;

import org.bank.bankaccount.application.request.AccountRequest;
import org.bank.bankaccount.application.response.AccountResponse;
import org.bank.bankaccount.application.response.BalanceResponse;
import org.bank.bankaccount.domain.exception.GenericException;
import org.bank.bankaccount.domain.model.Account;

import java.text.ParseException;

/**
 * Service to manage Accounts
 */
public interface AccountService {

    /**
     * Update account position
     *
     * @param account must not be null.
     * @param amount  must not be null.
     * @throws GenericException
     */
    void updateAccountPosition(Account account, Double amount) throws GenericException;

    /**
     * save an Account
     *
     * @param account must not be null.
     * @return guaranteed to be not null.
     * @throws GenericException
     */
    Account save(Account account) throws GenericException;

    /**
     * get an account by account number
     *
     * @param accountNumber must not be null.
     * @return guaranteed to be not null.
     * @throws GenericException
     */
    Account getAccount(int accountNumber) throws GenericException;

    /**
     * get an account balance by account number
     *
     * @param accountNumber must not be null.
     * @return guaranteed to be not null.
     * @throws GenericException
     */
    BalanceResponse getAccountBalance(Integer accountNumber) throws GenericException;

    /**
     * Create a new account
     *
     * @param accountRequest must not be null.
     * @return guaranteed to be not null.
     * @throws GenericException
     * @throws ParseException
     */
    AccountResponse createAccount(AccountRequest accountRequest) throws GenericException, ParseException;
}
