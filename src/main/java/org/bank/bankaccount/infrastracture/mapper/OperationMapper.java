package org.bank.bankaccount.infrastracture.mapper;

import org.bank.bankaccount.domain.model.Operation;
import org.bank.bankaccount.infrastracture.entity.OperationEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface OperationMapper {
    OperationMapper INSTANCE = Mappers.getMapper(OperationMapper.class);

    OperationEntity toEntity(Operation operation);

    Operation toDomain(OperationEntity operation);
}
