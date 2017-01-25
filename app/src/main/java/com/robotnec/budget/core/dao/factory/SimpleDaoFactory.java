package com.robotnec.budget.core.dao.factory;

import com.robotnec.budget.core.dao.AccountDao;
import com.robotnec.budget.core.dao.CategoryDao;
import com.robotnec.budget.core.dao.TransactionDao;

/**
 * @author zak <zak@swingpulse.com>
 */
public class SimpleDaoFactory implements DaoFactory {

    private final AccountDao mAccountDao;
    private final CategoryDao mCategoryDao;
    private final TransactionDao mTransactionDao;

    public SimpleDaoFactory() {
        mAccountDao = new SimpleAccountDao();
        mCategoryDao = new SimpleCategoryDao();
        mTransactionDao = new SimpleTransactionDao(mAccountDao, mCategoryDao);
    }

    public AccountDao getAccountDao() {
        return mAccountDao;
    }

    @Override
    public CategoryDao getCategoryDao() {
        return mCategoryDao;
    }

    @Override
    public TransactionDao getTransactionDao() {
        return mTransactionDao;
    }
}
