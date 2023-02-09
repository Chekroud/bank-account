package org.bank.bankaccount.infrastracture.repository.h2;

import org.bank.bankaccount.domain.model.Client;
import org.bank.bankaccount.domain.repository.ClientRepository;
import org.bank.bankaccount.infrastracture.entity.ClientEntity;
import org.bank.bankaccount.infrastracture.mapper.ClientMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class H2DbClientRepository implements ClientRepository {
    private final SpringDataH2ClientRepository clientRepository;

    public H2DbClientRepository(final SpringDataH2ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public Optional<Client> findByUsername(String username) {
        Optional<ClientEntity> client = clientRepository.findByUsername(username);
        return client.map(ClientEntity::toDomain);
    }

    @Override
    public Optional<Client> findByClientNumber(Integer clientNumber) {
        Optional<ClientEntity> client = clientRepository.findByClientNumber(clientNumber);
        return client.map(ClientEntity::toDomain);
    }

    @Override
    public Client save(Client client) {
        ClientEntity clientEntity = ClientMapper.INSTANCE.toEntity(client);
        return clientRepository.save(clientEntity).toDomain();
    }
}
