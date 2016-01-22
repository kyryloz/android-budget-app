package ua.com.zak.budgetswing.model.dao.factory;

import ua.com.zak.budgetswing.model.dao.AccountDao;
import ua.com.zak.budgetswing.model.dao.CategoryDao;

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
