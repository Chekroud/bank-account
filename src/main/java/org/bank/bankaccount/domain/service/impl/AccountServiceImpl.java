package org.bank.bankaccount.domain.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.bank.bankaccount.application.request.AccountRequest;
import org.bank.bankaccount.application.response.AccountResponse;
import org.bank.bankaccount.application.response.BalanceResponse;
import org.bank.bankaccount.domain.exception.GenericException;
import org.bank.bankaccount.domain.model.Account;
import org.bank.bankaccount.domain.model.Client;
import org.bank.bankaccount.domain.repository.AccountRepository;
import org.bank.bankaccount.domain.service.AccountService;
import org.bank.bankaccount.domain.service.ClientService;
import org.bank.bankaccount.domain.utils.Helper;
import org.bank.bankaccount.domain.utils.ValidatorUtils;
import org.bank.bankaccount.infrastracture.security.CustomUserDetailsService;
import org.bank.bankaccount.infrastracture.security.JwtTokenUtil;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.util.Optional;

@Slf4j
@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final CustomUserDetailsService userDetailsService;
    private final ClientService clientService;
    private final JwtTokenUtil jwtTokenUtil;

    public AccountServiceImpl(final AccountRepository accountRepository,
                              final CustomUserDetailsService userDetailsService,
                              final ClientService clientService,
                              final JwtTokenUtil jwtTokenUtil) {

        this.accountRepository = accountRepository;
        this.userDetailsService = userDetailsService;
        this.clientService = clientService;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @Override
    @Transactional
    public AccountResponse createAccount(AccountRequest accountRequest) throws GenericException, ParseException {
        log.info("Create a new account.");

        // save Client
        Client client = clientService.save(new Client(accountRequest));

        // generate account number
        Integer accountNumber;
        do {
            accountNumber = Helper.generateAccountNumber.getAsInt();
        } while (accountRepository.findByAccountNumber(accountNumber).isPresent());

        Account account = Account.builder()
                .accountNumber(accountNumber)
                .accountPosition(0D)
                .client(client)
                .build();

        account = this.save(account);

        return AccountResponse.builder()
                .accountNumber(account.getAccountNumber())
                .token(jwtTokenUtil.generateToken(client.getUsername()))
                .build();
    }

    @Override
    public void updateAccountPosition(Account account, Double amount) throws GenericException {
        Double newAccountPosition = account.getAccountPosition() + amount;
        ValidatorUtils.checkAccountPosition(newAccountPosition);
        log.info("Update account position.");

        account.setAccountPosition(newAccountPosition);
        accountRepository.save(account);
    }

    @Override
    public Account save(Account account) throws GenericException {
        ValidatorUtils.checkAccountNumber(account.getAccountNumber());
        log.info("Save an account.");
        return accountRepository.save(account);
    }

    @Override
    public Account getAccount(int accountNumber) throws GenericException {
        ValidatorUtils.checkAccountNumber(accountNumber);
        log.info("Get account : {}", accountNumber);

        // Get username of user connected
        UserDetails userDetails = userDetailsService.getUserConnected();
        String username = Optional.ofNullable(userDetails.getUsername()).orElse("");

        return accountRepository.findByAccountNumberAndUsername(accountNumber, username)
                .orElseThrow(() -> new GenericException("Account has not been found"));
    }


    @Override
    public BalanceResponse getAccountBalance(Integer accountNumber) throws GenericException {
        ValidatorUtils.checkAccountNumber(accountNumber);

        Account account = this.getAccount(accountNumber);
        return BalanceResponse.builder().accountNumber(accountNumber).accountBalance(account.getAccountPosition()).build();
    }

}
