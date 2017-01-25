package com.robotnec.budget.core.dao.factory;

import java.util.ArrayList;
import java.util.List;

import com.robotnec.budget.core.dao.AccountDao;
import com.robotnec.budget.core.dao.CategoryDao;
import com.robotnec.budget.core.dao.TransactionDao;
import com.robotnec.budget.core.domain.Account;
import com.robotnec.budget.core.domain.Transaction;

/**
 * @author zak <zak@swingpulse.com>
 */
class SimpleTransactionDao implements TransactionDao {

    private final AccountDao accountDao;
    private final CategoryDao categoryDao;

    private final List<Transaction> transactions;

    public SimpleTransactionDao(AccountDao accountDao, CategoryDao categoryDao) {
        this.accountDao = accountDao;
        this.categoryDao = categoryDao;
        transactions = new ArrayList<>();
    }

    @Override
    public List<Transaction> getAllTransactions() {
        return transactions;
    }

    @Override
    public boolean addTransaction(Transaction transaction) {
        Account account = transaction.getAccount();
        account.setAmount(account.getAmount() + transaction.getAmount());

        transactions.add(transaction);

        return true;
    }
}
