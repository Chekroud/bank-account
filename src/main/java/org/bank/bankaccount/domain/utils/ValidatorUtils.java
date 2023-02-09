package org.bank.bankaccount.domain.utils;

import org.bank.bankaccount.domain.exception.GenericException;

import java.util.Objects;

public class ValidatorUtils {

    public static void checkAccountPosition(Double accountPosition) throws GenericException {
        if (accountPosition < 0) {
            throw new GenericException("Operation refused, insufficient fund.");
        }
    }

    public static void checkAccountNumber(Integer accountNumber) throws GenericException {
        if (Objects.isNull(accountNumber) || accountNumber == 0) {
            throw new GenericException("Account Number is required.");
        }
    }

    public static void checkAmountOperation(Double amount) throws GenericException {
        if (Objects.isNull(amount) || amount == 0) {
            throw new GenericException("Amount of operation must be greater than 0.");
        }
    }
}
