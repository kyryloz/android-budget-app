package ua.com.zak.budgetswing.core;

import dagger.Module;
import dagger.Provides;
import ua.com.zak.budgetswing.core.dao.AccountDao;
import ua.com.zak.budgetswing.core.dao.factory.SimpleDaoFactory;

/**
 * @author zak <zak@swingpulse.com>
 */
@Module
public class DaoModule {

    @Provides
    public static AccountDao provideAccountDao() {
        return new SimpleDaoFactory().getAccountDao();
    }
}
