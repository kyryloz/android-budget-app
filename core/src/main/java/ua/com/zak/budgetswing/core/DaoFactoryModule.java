package ua.com.zak.budgetswing.core;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import ua.com.zak.budgetswing.core.dao.factory.DaoFactory;

/**
 * @author zak <zak@swingpulse.com>
 */
@Module
public class DaoFactoryModule {
    private final DaoFactory mDaoFactory;

    public DaoFactoryModule(DaoFactory daoFactory) {
        mDaoFactory = daoFactory;
    }

    @Singleton
    @Provides
    public DaoFactory provideDao() {
        return mDaoFactory;
    }
}
