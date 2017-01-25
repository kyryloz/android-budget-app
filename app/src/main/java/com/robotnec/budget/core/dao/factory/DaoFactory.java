package com.robotnec.budget.core.dao.factory;

import com.robotnec.budget.core.dao.AccountDao;
import com.robotnec.budget.core.dao.CategoryDao;
import com.robotnec.budget.core.dao.TransactionDao;

/**
 * @author zak <zak@swingpulse.com>
 */
public interface DaoFactory {
    AccountDao getAccountDao();

    CategoryDao getCategoryDao();

    TransactionDao getTransactionDao();
}
