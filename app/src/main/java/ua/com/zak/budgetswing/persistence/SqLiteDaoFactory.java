package ua.com.zak.budgetswing.persistence;

import ua.com.zak.budgetswing.core.dao.AccountDao;
import ua.com.zak.budgetswing.core.dao.CategoryDao;
import ua.com.zak.budgetswing.core.dao.TransactionDao;
import ua.com.zak.budgetswing.core.dao.factory.DaoFactory;

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
