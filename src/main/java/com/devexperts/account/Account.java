package com.devexperts.account;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return Objects.equals(accountKey, account.accountKey) && Objects.equals(firstName, account.firstName) && Objects.equals(lastName, account.lastName) && Objects.equals(balance, account.balance);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountKey, firstName, lastName, balance);
    }
}
