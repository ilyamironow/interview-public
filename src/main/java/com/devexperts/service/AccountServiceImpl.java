package com.devexperts.service;

import com.devexperts.account.Account;
import com.devexperts.account.AccountKey;
import com.devexperts.exceptions.AccountNotFoundException;
import com.devexperts.exceptions.InsufficientBalanceException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

@Service
public class AccountServiceImpl implements AccountService {
    private final Logger logger = Logger.getLogger(AccountServiceImpl.class.getName());

    private final Map<AccountKey, Account> accounts = new HashMap<>();

    @Override
    public void clear() {
        logger.info("Clearing account cache");
        accounts.clear();
    }

    @Override
    public void createAccount(Account account) {
        Account accountFromCache = accounts.get(account.getAccountKey());
        if (accountFromCache == null) {
            logger.info("Account successfully created: " + account);
            accounts.put(account.getAccountKey(), account);
        } else {
            String msg = "account is already present in cache: " + accountFromCache;
            logger.warning(msg);
            throw new IllegalArgumentException(msg);
        }
    }

    @Override
    public Account getAccount(long id) {
        return accounts.get(AccountKey.valueOf(id));
    }

    @Override
    public void transfer(Account source, Account target, double amount) {
        if (source == null) {
            throw new AccountNotFoundException("source account was not found", null);
        } else if (target == null) {
            throw new AccountNotFoundException("target account was not found", null);
        } else if (source.equals(target)) {
            throw new IllegalArgumentException("accounts should not be equal");
        } else if (!accounts.containsKey(source.getAccountKey())) {
            throw new AccountNotFoundException("source account was not found", source.getAccountKey());
        } else if (!accounts.containsKey(target.getAccountKey())) {
            throw new AccountNotFoundException("target account was not found", target.getAccountKey());
        } else if (amount <= 0) {
            throw new IllegalArgumentException("transferred amount should be positive");
        }
        Double sourceBalance = source.getBalance();
        if (sourceBalance >= amount) {
            source.setBalance(sourceBalance - amount);
            target.setBalance(target.getBalance() + amount);
            logger.info("Successfully transferred " + amount + "$ from account " + source.getAccountKey() + " to " + target.getAccountKey());
        } else {
            throw new InsufficientBalanceException("source account doesn't have required amount of money",
                    source.getAccountKey());
        }

    }
}
