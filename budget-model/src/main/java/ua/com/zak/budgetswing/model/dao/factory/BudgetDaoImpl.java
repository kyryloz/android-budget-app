package ua.com.zak.budgetswing.model.dao.factory;

import java.util.ArrayList;
import java.util.List;

import ua.com.zak.budgetswing.model.dao.BudgetDao;
import ua.com.zak.budgetswing.model.domen.Account;

class BudgetDaoImpl implements BudgetDao {

    private final List<Account> mAccounts;

    public BudgetDaoImpl() {
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
    public boolean removeAccount(long accountId) {
        return mAccounts.remove(findById(accountId));
    }

    private Account findById(long accountId) {
        for (Account account : mAccounts) {
            if (account.getId() == accountId) {
                return account;
            }
        }
        throw new IllegalArgumentException("Can't find account with id: " + accountId);
    }
}
