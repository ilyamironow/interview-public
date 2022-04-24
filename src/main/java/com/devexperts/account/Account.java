package com.devexperts.account;

import static java.util.Objects.requireNonNull;

public class Account {
    private final AccountKey accountKey;
    private final String firstName;
    private final String lastName;
    // Double is not good enough for folding money values, it's better to use BigDecimal.
    private Double balance;

    public Account(AccountKey accountKey, String firstName, String lastName, Double balance) {
        this.accountKey = requireNonNull(accountKey, "accountKey");
        this.firstName = firstName;
        this.lastName = lastName;
        this.balance = requireNonNull(balance, "balance");
    }

    public AccountKey getAccountKey() {
        return accountKey;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountKey=" + accountKey +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", balance=" + balance +
                '}';
    }
}
