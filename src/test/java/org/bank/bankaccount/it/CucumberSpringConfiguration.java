package org.bank.bankaccount.it;

import org.bank.bankaccount.BankAccountApplication;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.DefaultDataTableCellTransformer;
import io.cucumber.java.DefaultDataTableEntryTransformer;
import io.cucumber.java.DefaultParameterTransformer;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

import java.lang.reflect.Type;

@CucumberContextConfiguration
@SpringBootTest(classes = BankAccountApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CucumberSpringConfiguration {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @DefaultDataTableCellTransformer
    @DefaultDataTableEntryTransformer
    @DefaultParameterTransformer
    public Object transform(final Object from, final Type to) {
        return objectMapper.convertValue(from, objectMapper.constructType(to));
    }
}
