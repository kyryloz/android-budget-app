package ua.com.zak.budgetswing.core.dao.factory;

import ua.com.zak.budgetswing.core.dao.AccountDao;
import ua.com.zak.budgetswing.core.dao.CategoryDao;

/**
 * @author zak <zak@swingpulse.com>
 */
public class SimpleDaoFactory implements DaoFactory {

    private final AccountDao mAccountDao;
    private final CategoryDao mCategoryDao;

    public SimpleDaoFactory() {
        mAccountDao = new SimpleAccountDao();
        mCategoryDao = new SimpleCategoryDao();
    }

    public AccountDao getAccountDao() {
        return mAccountDao;
    }

    @Override
    public CategoryDao getCategoryDao() {
        return mCategoryDao;
    }
}
