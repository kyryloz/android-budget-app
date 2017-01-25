package com.robotnec.budget.core.di;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import com.robotnec.budget.core.dao.AccountDao;
import com.robotnec.budget.core.dao.CategoryDao;
import com.robotnec.budget.core.dao.TransactionDao;
import com.robotnec.budget.core.dao.factory.DaoFactory;

/**
 * @author zak <zak@swingpulse.com>
 */
@Module
public class DaoModule {

    private final DaoFactory daoFactory;

    public DaoModule(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    @Singleton
    @Provides
    public AccountDao provideAccountDao() {
        return daoFactory.getAccountDao();
    }

    @Singleton
    @Provides
    public CategoryDao provideCategoryDao() {
        return daoFactory.getCategoryDao();
    }

    @Singleton
    @Provides
    public TransactionDao provideTransactionDao() {
        return daoFactory.getTransactionDao();
    }
}
