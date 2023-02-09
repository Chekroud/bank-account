package org.bank.bankaccount.domain.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.bank.bankaccount.domain.model.Client;
import org.bank.bankaccount.domain.repository.ClientRepository;
import org.bank.bankaccount.domain.service.ClientService;
import org.bank.bankaccount.domain.utils.Helper;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ClientServiceImpl implements ClientService {
    private final ClientRepository clientRepository;

    public ClientServiceImpl(final ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public Client save(Client newClient) {
        log.info("Save a new client");

        // get client if exist
        Client client = clientRepository.findByUsername(newClient.getUsername()).orElse(newClient);

        // generate client number
        Integer clientNumber;
        do {
            clientNumber = Helper.generateAccountNumber.getAsInt();
        } while (clientRepository.findByClientNumber(clientNumber).isPresent());

        client.setClientNumber(clientNumber);
        return clientRepository.save(client);
    }
}
