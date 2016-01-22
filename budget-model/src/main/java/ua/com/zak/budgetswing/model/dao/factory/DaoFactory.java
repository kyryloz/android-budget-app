package ua.com.zak.budgetswing.model.dao.factory;

import ua.com.zak.budgetswing.model.dao.AccountDao;
import ua.com.zak.budgetswing.model.dao.CategoryDao;

/**
 * @author zak <zak@swingpulse.com>
 */
public interface DaoFactory {
    AccountDao getAccountDao();
    CategoryDao getCategoryDao();
}
