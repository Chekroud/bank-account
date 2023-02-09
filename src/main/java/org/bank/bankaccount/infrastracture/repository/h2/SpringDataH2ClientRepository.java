package org.bank.bankaccount.infrastracture.repository.h2;

import org.bank.bankaccount.infrastracture.entity.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SpringDataH2ClientRepository extends JpaRepository<ClientEntity, Long> {

    Optional<ClientEntity> findByUsername(String username);

    Optional<ClientEntity> findByClientNumber(Integer clientNumber);
}
