package ua.com.zak.budgetswing.core;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import ua.com.zak.budgetswing.core.dao.AccountDao;
import ua.com.zak.budgetswing.core.dao.CategoryDao;
import ua.com.zak.budgetswing.core.dao.factory.DaoFactory;

/**
 * @author zak <zak@swingpulse.com>
 */
@Module
public class DaoModule {

    private final DaoFactory mDaoFactory;

    public DaoModule(DaoFactory daoFactory) {
        mDaoFactory = daoFactory;
    }

    @Singleton
    @Provides
    public AccountDao provideAccountDao() {
        return mDaoFactory.getAccountDao();
    }

    @Singleton
    @Provides
    public CategoryDao provideCategoryDao() {
        return mDaoFactory.getCategoryDao();
    }
}
