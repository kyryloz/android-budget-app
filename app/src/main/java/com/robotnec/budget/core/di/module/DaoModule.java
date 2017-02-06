package com.robotnec.budget.core.di.module;

import com.robotnec.budget.app.persistence.BudgetDatabase;
import com.robotnec.budget.app.persistence.SquidbAccountDao;
import com.robotnec.budget.app.persistence.SquidbCategoryDao;
import com.robotnec.budget.app.persistence.SquidbTransactionDao;
import com.robotnec.budget.app.service.SimpleCurrencyExchangeService;
import com.robotnec.budget.core.dao.AccountDao;
import com.robotnec.budget.core.dao.CategoryDao;
import com.robotnec.budget.core.dao.TransactionDao;
import com.robotnec.budget.core.service.CurrencyExchangeService;

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
        return new SquidbCategoryDao(database);
    }

    @Singleton
    @Provides
    public CurrencyExchangeService provideCurrencyExchangeService() {
        return new SimpleCurrencyExchangeService();
    }

    @Singleton
    @Provides
    public TransactionDao provideTransactionDao(AccountDao accountDao,
                                                CategoryDao categoryDao,
                                                CurrencyExchangeService exchangeService) {
        return new SquidbTransactionDao(database, accountDao, categoryDao, exchangeService);
    }
}
