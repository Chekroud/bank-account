package org.bank.bankaccount.domain.repository;

import org.bank.bankaccount.domain.model.Client;

import java.util.Optional;

/**
 * Interface to manage client in database
 */
public interface ClientRepository {

    /**
     * get Client by username client
     *
     * @param username must not be null.
     * @return guaranteed to be not null.
     */
    Optional<Client> findByUsername(String username);

    /**
     * get Client by client number
     *
     * @param clientNumber must not be null.
     * @return guaranteed to be not null.
     */
    Optional<Client> findByClientNumber(Integer clientNumber);

    /**
     * save an Client
     *
     * @param client must not be null.
     * @return guaranteed to be not null.
     */
    Client save(Client client);
}
