package org.bank.bankaccount.it.steps;

import org.bank.bankaccount.application.request.OperationRequest;
import org.bank.bankaccount.application.response.BalanceResponse;
import org.bank.bankaccount.application.response.OperationHistoryResponse;
import org.bank.bankaccount.domain.model.Operation;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class AccountSteps {
    @LocalServerPort
    private int port;

    private RestTemplate restTemplate = new RestTemplate();
    private HttpHeaders headers = new HttpHeaders();
    private String baseUrl = "http://localhost:";
    private Integer accountNumber;
    private ResponseEntity<?> response;

    @Given("an account exist with the number {int}")
    public void an_account_exist_with_the_number(Integer accountNumber) {
        this.accountNumber = accountNumber;
        String token = "eyJhbGciOiJIUzUxMiJ9.eyJ1c2VybmFtZSI6Im1jaGVrcm91ZCIsImlhdCI6MTY3NTg5NzMwMywiZXhwIjoxODkzNDUyNDAwfQ.kddLFHPTCOWN6o0WthAZd1cygRiA0O_WSr9WO4rW03KbeBTF-2uHvdVOWS_PbyTA1VwNN_ir7nsZRf-PV30Zxw";
        headers.set("Authorization", "Bearer " + token);
    }

    @When("a client asks for his balance")
    public void a_client_asks_for_his_balance() {
        String url = baseUrl + port + "/accounts/" + accountNumber + "/balance";
        response = new RestTemplate().exchange(url, HttpMethod.GET, new HttpEntity<>(headers), BalanceResponse.class);
    }

    @When("client makes a deposit of {double} in his account")
    public void client_makes_a_deposit_of_in_his_account(Double amount) {
        String url = baseUrl + port + "/operations/";
        OperationRequest operationRequest = OperationRequest.builder().accountNumber(accountNumber).amount(amount).build();
        response = restTemplate.exchange(url, HttpMethod.POST, new HttpEntity<>(operationRequest, headers), String.class);
    }

    @When("client makes a withdrawal of {double} from his account")
    public void client_makes_a_withdrawal_of_from_his_account(Double amount) {
        String url = baseUrl + port + "/operations/";
        OperationRequest operationRequest = OperationRequest.builder().accountNumber(accountNumber).amount(amount * (-1)).build();
        response = restTemplate.exchange(url, HttpMethod.POST, new HttpEntity<>(operationRequest, headers), String.class);
    }

    @When("a client asks for his history")
    public void a_client_asks_for_his_history() {
        String url = baseUrl + port + "/operations/" + accountNumber;
        response = new RestTemplate().exchange(url, HttpMethod.GET, new HttpEntity<>(headers), OperationHistoryResponse.class);
    }

    @Then("the response status should be {int}")
    public void the_response_status_should_be(Integer status) {
        assertEquals(status, response.getStatusCode().value());
    }

    @Then("the balance should be {double}")
    public void the_balance_should_be(Double balance) {
        BalanceResponse balanceResponse = (BalanceResponse) response.getBody();
        assertEquals(balance, balanceResponse.getAccountBalance());
    }

    @Then("the response should contains")
    public void the_response_should_contains(List<Operation> operations) {
        OperationHistoryResponse history = (OperationHistoryResponse) response.getBody();

        assertNotNull(operations);
        assertNotNull(history);
        assertNotNull(history.getOperations());
        assertEquals(2, history.getSize());
        assertEquals(operations.size(), history.getSize());

        for (int i = 0; i < operations.size(); i++) {
            assertEquals(operations.get(i).getType(), history.getOperations().get(i).getType());
            assertEquals(operations.get(i).getAmount(), history.getOperations().get(i).getAmount());
            assertEquals(operations.get(i).getBalance(), history.getOperations().get(i).getBalance());
        }
    }

}
