package org.bank.bankaccount.infrastracture.mapper;

import org.bank.bankaccount.domain.model.Account;
import org.bank.bankaccount.infrastracture.entity.AccountEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface AccountMapper {
    AccountMapper INSTANCE = Mappers.getMapper(AccountMapper.class);

    AccountEntity toEntity(Account account);

    Account toDomain(AccountEntity account);
}
