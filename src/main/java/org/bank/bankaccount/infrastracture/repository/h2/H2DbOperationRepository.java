package org.bank.bankaccount.infrastracture.repository.h2;

import org.bank.bankaccount.domain.model.Account;
import org.bank.bankaccount.domain.model.Operation;
import org.bank.bankaccount.domain.repository.OperationRepository;
import org.bank.bankaccount.infrastracture.entity.AccountEntity;
import org.bank.bankaccount.infrastracture.entity.OperationEntity;
import org.bank.bankaccount.infrastracture.mapper.AccountMapper;
import org.bank.bankaccount.infrastracture.mapper.OperationMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class H2DbOperationRepository implements OperationRepository {
    private final SpringDataH2OperationRepository operationRepository;

    public H2DbOperationRepository(final SpringDataH2OperationRepository operationRepository) {
        this.operationRepository = operationRepository;
    }

    @Override
    public Operation save(Operation operation) {
        OperationEntity operationEntity = OperationMapper.INSTANCE.toEntity(operation);
        return operationRepository.save(operationEntity).toDomain();
    }

    @Override
    public List<Operation> findByAccount(Account account, Pageable pageable) {
        AccountEntity accountEntity = AccountMapper.INSTANCE.toEntity(account);
        List<OperationEntity> list = operationRepository.findByAccount(accountEntity, pageable);
        return list.stream().map(OperationEntity::toDomain).toList();
    }
}
