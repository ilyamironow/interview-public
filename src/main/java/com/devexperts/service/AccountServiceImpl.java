package com.devexperts.service;

import com.devexperts.account.Account;
import com.devexperts.account.AccountKey;
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
        //do nothing for now
    }
}
