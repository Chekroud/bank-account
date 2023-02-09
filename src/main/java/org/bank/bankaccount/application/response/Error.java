package org.bank.bankaccount.application.response;

import lombok.Getter;

@Getter
public class Error {
    private String code;
    private String message;

    public Error(String message) {
        this.message = message;
        this.code = "";
    }

    public Error(String message, String code) {
        this(message);
        this.code = code;
    }
}
