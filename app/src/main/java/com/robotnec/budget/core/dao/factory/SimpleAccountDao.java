package com.robotnec.budget.core.dao.factory;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.robotnec.budget.core.dao.AccountDao;
import com.robotnec.budget.core.domain.Account;

public class SimpleAccountDao implements AccountDao {

    private final List<Account> accounts;

    public SimpleAccountDao() {
        accounts = new ArrayList<>();
        Account accountCash = new Account();
        accountCash.setId(1);
        accountCash.setAmount(new BigDecimal(20000));
        accountCash.setCurrencyId(1);
        accountCash.setName("My Cash");

        Account accountBank = new Account();
        accountBank.setId(2);
        accountBank.setAmount(new BigDecimal(10000));
        accountBank.setCurrencyId(1);
        accountBank.setName("Bank");

        accounts.add(accountCash);
        accounts.add(accountBank);
    }

    @Override
    public List<Account> getAllAccounts() {
        return accounts;
    }

    @Override
    public boolean addAccount(Account account) {
        return accounts.add(account);
    }

    @Override
    public void updateAccount(Account account) {
        Account edited = findById(account.getId());
        edited.setAmount(account.getAmount());
        edited.setName(account.getName());
    }

    @Override
    public boolean removeAccount(long accountId) {
        return accounts.remove(findById(accountId));
    }

    @Override
    public Account findById(long accountId) {
        for (Account account : accounts) {
            if (account.getId() == accountId) {
                return account;
            }
        }
        throw new IllegalArgumentException("Can't find account with id: " + accountId);
    }
}
