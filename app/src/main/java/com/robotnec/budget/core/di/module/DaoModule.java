package com.robotnec.budget.core.di.module;

import com.robotnec.budget.app.persistence.BudgetDatabase;
import com.robotnec.budget.app.persistence.SquidbAccountDao;
import com.robotnec.budget.core.dao.AccountDao;
import com.robotnec.budget.core.dao.CategoryDao;
import com.robotnec.budget.core.dao.TransactionDao;
import com.robotnec.budget.core.dao.factory.SimpleCategoryDao;
import com.robotnec.budget.core.dao.factory.SimpleTransactionDao;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * @author zak <zak@swingpulse.com>
 */
@Module
public class DaoModule {

    private final BudgetDatabase database;

    public DaoModule(BudgetDatabase database) {
        this.database = database;
    }

    @Singleton
    @Provides
    public AccountDao provideAccountDao() {
        return new SquidbAccountDao(database);
    }

    @Singleton
    @Provides
    public CategoryDao provideCategoryDao() {
        return new SimpleCategoryDao();
    }

    @Singleton
    @Provides
    public TransactionDao provideTransactionDao(AccountDao accountDao, CategoryDao categoryDao) {
        return new SimpleTransactionDao(accountDao, categoryDao);
    }
}
