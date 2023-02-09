package org.bank.bankaccount.domain.service;

import org.bank.bankaccount.domain.exception.GenericException;
import org.bank.bankaccount.domain.model.Client;

/**
 * Service to manage Clients
 */
public interface ClientService {

    /**
     * save an Client
     *
     * @param client must not be null.
     * @return guaranteed to be not null.
     * @throws GenericException
     */
    Client save(Client client) throws GenericException;
}
