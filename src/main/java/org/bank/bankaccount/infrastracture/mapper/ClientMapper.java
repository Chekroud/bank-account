package org.bank.bankaccount.infrastracture.mapper;

import org.bank.bankaccount.domain.model.Client;
import org.bank.bankaccount.infrastracture.entity.ClientEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ClientMapper {
    ClientMapper INSTANCE = Mappers.getMapper(ClientMapper.class);

    ClientEntity toEntity(Client client);

    Client toDomain(ClientEntity client);
}
