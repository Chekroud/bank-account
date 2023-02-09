package org.bank.bankaccount.infrastracture.repository.h2;

import org.bank.bankaccount.infrastracture.entity.AccountEntity;
import org.bank.bankaccount.infrastracture.entity.OperationEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpringDataH2OperationRepository extends JpaRepository<OperationEntity, Long> {

    List<OperationEntity> findByAccount(AccountEntity account, Pageable pageable);
}
