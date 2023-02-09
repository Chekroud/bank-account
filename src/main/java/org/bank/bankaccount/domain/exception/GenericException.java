package org.bank.bankaccount.domain.exception;

import lombok.Getter;

@Getter
public class GenericException extends Exception {

    private String code;

    public GenericException(String message) {
        super(message);
    }

    public GenericException(String message, String code) {
        super(message);
        this.code = code;
    }
}
