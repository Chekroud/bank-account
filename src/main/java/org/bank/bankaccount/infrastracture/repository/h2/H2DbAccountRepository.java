package org.bank.bankaccount.infrastracture.repository.h2;

import org.bank.bankaccount.domain.model.Account;
import org.bank.bankaccount.domain.repository.AccountRepository;
import org.bank.bankaccount.infrastracture.entity.AccountEntity;
import org.bank.bankaccount.infrastracture.mapper.AccountMapper;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class H2DbAccountRepository implements AccountRepository {
    private final SpringDataH2AccountRepository accountRepository;

    public H2DbAccountRepository(final SpringDataH2AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public Optional<Account> findByAccountNumber(Integer accountNumber) {
        Optional<AccountEntity> account = accountRepository.findByAccountNumber(accountNumber);
        return account.map(AccountEntity::toDomain);
    }

    @Override
    public Optional<Account> findByAccountNumberAndUsername(Integer accountNumber, String username) {
        Optional<AccountEntity> account = accountRepository.findByAccountNumberAndUsername(accountNumber, username);
        return account.map(AccountEntity::toDomain);
    }

    @Override
    public Account save(Account account) {
        AccountEntity accountEntity = AccountMapper.INSTANCE.toEntity(account);
        return accountRepository.save(accountEntity).toDomain();
    }
}
