package org.bank.bankaccount.application.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.bank.bankaccount.application.request.OperationRequest;
import org.bank.bankaccount.application.response.OperationHistoryResponse;
import org.bank.bankaccount.domain.exception.GenericException;
import org.bank.bankaccount.domain.service.OperationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@SecurityRequirement(name = "custom-bank-jwt-api")
@RestController
@RequestMapping("/operations")
@Tag(name = "Operation Controller", description = "APIs to manage operations")
public class OperationController {
    private final OperationService operationService;

    public OperationController(final OperationService operationService) {
        this.operationService = operationService;
    }

    @Operation(summary = "Deposit Or Withdrawal")
    @Secured("ROLE_CLIENT")
    @PostMapping(path = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> executeOperation(@Validated @RequestBody OperationRequest operationRequest) throws GenericException {

        return new ResponseEntity<>(operationService.executeOperation(operationRequest), HttpStatus.CREATED);
    }

    @Operation(summary = "Operation history")
    @Secured("ROLE_CLIENT")
    @GetMapping("/{accountNumber}")
    public ResponseEntity<OperationHistoryResponse> getAllOperations(
            @PathVariable(value = "accountNumber") Integer accountNumber,
            @RequestParam(value = "page", required = false, defaultValue = "0") int page,
            @RequestParam(value = "size", required = false, defaultValue = "10") int size) throws GenericException {

        return ResponseEntity.ok(operationService.getAllOperations(accountNumber, page, size));
    }
}
