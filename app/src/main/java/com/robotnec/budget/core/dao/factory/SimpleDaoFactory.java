package com.robotnec.budget.core.dao.factory;

import com.robotnec.budget.core.dao.AccountDao;
import com.robotnec.budget.core.dao.CategoryDao;
import com.robotnec.budget.core.dao.TransactionDao;

/**
 * @author zak <zak@swingpulse.com>
 */
public class SimpleDaoFactory implements DaoFactory {

    private final AccountDao accountDao;
    private final CategoryDao categoryDao;
    private final TransactionDao transactionDao;

    public SimpleDaoFactory() {
        accountDao = new SimpleAccountDao();
        categoryDao = new SimpleCategoryDao();
        transactionDao = new SimpleTransactionDao(accountDao, categoryDao);
    }

    public AccountDao getAccountDao() {
        return accountDao;
    }

    @Override
    public CategoryDao getCategoryDao() {
        return categoryDao;
    }

    @Override
    public TransactionDao getTransactionDao() {
        return transactionDao;
    }
}
