package com.devexperts.exceptions;

import com.devexperts.account.AccountKey;

/**
 * Exception is thrown when trying to transfer money from account
 * that doesn't have enough amount of money.
 */
public class InsufficientBalanceException extends RuntimeException {
    private final AccountKey accountKey;

    public InsufficientBalanceException(String message, AccountKey accountKey) {
        super(message);
        this.accountKey = accountKey;
    }

    public AccountKey getAccountKey() {
        return accountKey;
    }
}
