package ua.com.zak.budgetswing.model.dao.factory;

import java.util.ArrayList;
import java.util.List;

import ua.com.zak.budgetswing.model.dao.AccountDao;
import ua.com.zak.budgetswing.model.domen.Account;
import ua.com.zak.budgetswing.model.domen.Transaction;

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
    public boolean removeAccount(long accountId) {
        return mAccounts.remove(findById(accountId));
    }

    @Override
    public boolean makeTransaction(Transaction transaction) {
        Account account = findById(transaction.getAccountId());
        account.setAmount(account.getAmount() + transaction.getAmount());
        return true;
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
