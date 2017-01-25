package com.robotnec.budget.core.dao.factory;

import java.util.ArrayList;
import java.util.List;

import com.robotnec.budget.core.dao.AccountDao;
import com.robotnec.budget.core.domain.Account;

class SimpleAccountDao implements AccountDao {

    private final List<Account> mAccounts;

    public SimpleAccountDao() {
        mAccounts = new ArrayList<>();
        Account accountCash = new Account();
        accountCash.setId(1);
        accountCash.setAmount(2000);
        accountCash.setCurrencyCode("UAH");
        accountCash.setName("My Cash");

        Account accountBank = new Account();
        accountBank.setId(2);
        accountBank.setAmount(12000);
        accountBank.setCurrencyCode("USD");
        accountBank.setName("Bank");

        mAccounts.add(accountCash);
        mAccounts.add(accountBank);
    }

    @Override
    public List<Account> getAllAccounts() {
        return mAccounts;
    }

    @Override
    public boolean addAccount(Account account) {
        return mAccounts.add(account);
    }

    @Override
    public void updateAccount(Account account) {
        Account edited = findById(account.getId());
        edited.setAmount(account.getAmount());
        edited.setName(account.getName());
    }

    @Override
    public boolean removeAccount(long accountId) {
        return mAccounts.remove(findById(accountId));
    }

    @Override
    public Account findById(long accountId) {
        for (Account account : mAccounts) {
            if (account.getId() == accountId) {
                return account;
            }
        }
        throw new IllegalArgumentException("Can't find account with id: " + accountId);
    }
}
