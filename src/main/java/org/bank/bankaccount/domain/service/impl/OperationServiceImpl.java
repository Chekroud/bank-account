package org.bank.bankaccount.domain.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.bank.bankaccount.application.request.OperationRequest;
import org.bank.bankaccount.application.response.OperationHistoryResponse;
import org.bank.bankaccount.domain.exception.GenericException;
import org.bank.bankaccount.domain.model.Account;
import org.bank.bankaccount.domain.model.Operation;
import org.bank.bankaccount.domain.repository.OperationRepository;
import org.bank.bankaccount.domain.service.AccountService;
import org.bank.bankaccount.domain.service.OperationService;
import org.bank.bankaccount.domain.utils.Helper;
import org.bank.bankaccount.domain.utils.ValidatorUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class OperationServiceImpl implements OperationService {
    private final OperationRepository operationRepository;
    private final AccountService accountService;

    public OperationServiceImpl(final OperationRepository operationRepository,
                                final AccountService accountService) {
        this.operationRepository = operationRepository;
        this.accountService = accountService;
    }

    @Override
    @Transactional
    public String executeOperation(OperationRequest operationRequest) throws GenericException {
        ValidatorUtils.checkAmountOperation(operationRequest.getAmount());
        log.info("Execute a new operation.");

        Account account = accountService.getAccount(operationRequest.getAccountNumber());
        accountService.updateAccountPosition(account, operationRequest.getAmount());
        this.save(operationRequest.getAmount(), account);

        return "Account position updated.";
    }

    @Override
    public void save(Double amount, Account account) {
        log.info("Save a new operation.");
        Operation operation = Operation.builder()
                .type(Helper.evaluateOperationType.apply(amount))
                .amount(amount)
                .account(account)
                .balance(account.getAccountPosition())
                .date(new Date())
                .build();

        operationRepository.save(operation);
    }

    @Override
    public OperationHistoryResponse getAllOperations(Integer accountNumber, int page, int size) throws GenericException {
        ValidatorUtils.checkAccountNumber(accountNumber);
        log.info("call operation history.");

        PageRequest pageRequest = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "date"));

        Account account = accountService.getAccount(accountNumber);
        List<Operation> operations = operationRepository.findByAccount(account, pageRequest);
        return new OperationHistoryResponse(operations);
    }
}
