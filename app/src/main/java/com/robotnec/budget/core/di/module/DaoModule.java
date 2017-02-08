package com.robotnec.budget.core.di.module;

import com.robotnec.budget.core.persistence.BudgetDatabase;
import com.robotnec.budget.core.persistence.TransactionContext;
import com.robotnec.budget.core.persistence.TransactionContextImpl;
import com.robotnec.budget.core.persistence.dao.impl.AccountDaoImpl;
import com.robotnec.budget.core.persistence.dao.impl.CategoryDaoImpl;
import com.robotnec.budget.core.persistence.dao.impl.MoneyOperationDaoImpl;
import com.robotnec.budget.core.persistence.dao.AccountDao;
import com.robotnec.budget.core.persistence.dao.CategoryDao;
import com.robotnec.budget.core.persistence.dao.MoneyOperationDao;

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
        return new AccountDaoImpl(database);
    }

    @Singleton
    @Provides
    public CategoryDao provideCategoryDao() {
        return new CategoryDaoImpl(database);
    }

    @Singleton
    @Provides
    public MoneyOperationDao provideMoneyOperationDao(AccountDao accountDao,
                                                   CategoryDao categoryDao) {
        return new MoneyOperationDaoImpl(database, accountDao, categoryDao);
    }

    @Singleton
    @Provides
    public TransactionContext provideTransactionContext() {
        return new TransactionContextImpl(database);
    }
}
