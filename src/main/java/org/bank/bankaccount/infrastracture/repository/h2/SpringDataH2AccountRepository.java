package org.bank.bankaccount.infrastracture.repository.h2;

import org.bank.bankaccount.infrastracture.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * AccountRepository
 */
@Repository
public interface SpringDataH2AccountRepository extends JpaRepository<AccountEntity, Long> {

    Optional<AccountEntity> findByAccountNumber(Integer accountNumber);

    @Query("SELECT a FROM account a INNER JOIN a.client c WHERE c.username = :username AND a.accountNumber = :accountNumber")
    Optional<AccountEntity> findByAccountNumberAndUsername(Integer accountNumber, String username);

}
