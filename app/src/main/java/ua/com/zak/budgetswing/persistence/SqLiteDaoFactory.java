package ua.com.zak.budgetswing.persistence;

import ua.com.zak.budgetswing.model.dao.AccountDao;
import ua.com.zak.budgetswing.model.dao.CategoryDao;
import ua.com.zak.budgetswing.model.dao.factory.DaoFactory;

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
}
