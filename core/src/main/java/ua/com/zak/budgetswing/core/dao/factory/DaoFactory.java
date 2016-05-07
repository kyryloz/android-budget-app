package ua.com.zak.budgetswing.core.dao.factory;

import ua.com.zak.budgetswing.core.dao.AccountDao;
import ua.com.zak.budgetswing.core.dao.CategoryDao;
import ua.com.zak.budgetswing.core.dao.TransactionDao;

/**
 * @author zak <zak@swingpulse.com>
 */
public interface DaoFactory {
    AccountDao getAccountDao();
    CategoryDao getCategoryDao();
    TransactionDao getTransactionDao();
}
