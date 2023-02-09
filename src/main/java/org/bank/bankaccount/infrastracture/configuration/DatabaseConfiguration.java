package org.bank.bankaccount.infrastracture.configuration;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EntityScan("org.bank.bankaccount.infrastracture.entity")
@EnableJpaRepositories("org.bank.bankaccount.infrastracture.repository")
public class DatabaseConfiguration {
}
