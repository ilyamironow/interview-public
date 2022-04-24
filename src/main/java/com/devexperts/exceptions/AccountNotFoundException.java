package com.devexperts.exceptions;

import com.devexperts.account.AccountKey;

/**
 * Exception is thrown when trying to transfer money from account
 * that was not found in our storage.
 */
public class AccountNotFoundException extends RuntimeException {
    private final AccountKey accountKey;

    public AccountNotFoundException(String message, AccountKey accountKey) {
        super(message);
        this.accountKey = accountKey;
    }

    public AccountKey getAccountKey() {
        return accountKey;
    }
}
