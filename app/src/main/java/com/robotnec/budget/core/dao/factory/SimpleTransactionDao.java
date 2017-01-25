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

    private final AccountDao mAccountDao;
    private final CategoryDao mCategoryDao;

    private final List<Transaction> mTransactions;

    public SimpleTransactionDao(AccountDao accountDao, CategoryDao categoryDao) {
        mAccountDao = accountDao;
        mCategoryDao = categoryDao;
        mTransactions = new ArrayList<>();
    }

    @Override
    public List<Transaction> getAllTransactions() {
        return mTransactions;
    }

    @Override
    public boolean addTransaction(Transaction transaction) {
        Account account = transaction.getAccount();
        account.setAmount(account.getAmount() + transaction.getAmount());

        mTransactions.add(transaction);

        return true;
    }
}
