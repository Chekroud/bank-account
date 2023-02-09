package org.bank.bankaccount.application.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.bank.bankaccount.application.request.AccountRequest;
import org.bank.bankaccount.application.response.AccountResponse;
import org.bank.bankaccount.application.response.BalanceResponse;
import org.bank.bankaccount.domain.exception.GenericException;
import org.bank.bankaccount.domain.service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

@SecurityRequirement(name = "custom-bank-jwt-api")
@RestController
@RequestMapping("/accounts")
@Tag(name = "Account Controller", description = "Create an account and generate Token")
public class AccountController {
    private final AccountService accountService;

    public AccountController(final AccountService accountService) {
        this.accountService = accountService;
    }

    @Operation(summary = "Create a new account")
    @PostMapping(path = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AccountResponse> createAccount(@Validated @RequestBody AccountRequest accountRequest) throws GenericException, ParseException {

        return new ResponseEntity<>(accountService.createAccount(accountRequest), HttpStatus.CREATED);
    }

    @Operation(summary = "Get the account balance")
    @Secured("ROLE_CLIENT")
    @GetMapping("/{accountNumber}/balance")
    public ResponseEntity<BalanceResponse> getAccountBalance(@PathVariable("accountNumber") final Integer accountNumber) throws GenericException {

        return ResponseEntity.ok(accountService.getAccountBalance(accountNumber));
    }
}
