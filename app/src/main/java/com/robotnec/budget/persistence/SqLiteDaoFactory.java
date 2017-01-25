package com.robotnec.budget.persistence;

import com.robotnec.budget.core.dao.AccountDao;
import com.robotnec.budget.core.dao.CategoryDao;
import com.robotnec.budget.core.dao.TransactionDao;
import com.robotnec.budget.core.dao.factory.DaoFactory;

/**
 * @author zak <zak@swingpulse.com>
 */
public class SqLiteDaoFactory implements DaoFactory {

    @Override
    public AccountDao getAccountDao() {
        return null;
    }

    @Override
    public CategoryDao getCategoryDao() {
        return null;
    }

    @Override
    public TransactionDao getTransactionDao() {
        return null;
    }
}
