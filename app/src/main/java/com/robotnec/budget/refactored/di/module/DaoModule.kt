package com.robotnec.budget.refactored.di.module

import android.app.Application
import com.robotnec.budget.core.persistence.BudgetDatabase
import com.robotnec.budget.core.persistence.TransactionContext
import com.robotnec.budget.core.persistence.TransactionContextImpl
import com.robotnec.budget.core.persistence.dao.AccountDao
import com.robotnec.budget.core.persistence.dao.CategoryDao
import com.robotnec.budget.core.persistence.dao.TransactionDao
import com.robotnec.budget.core.persistence.dao.impl.AccountDaoImpl
import com.robotnec.budget.core.persistence.dao.impl.CategoryDaoImpl
import com.robotnec.budget.core.persistence.dao.impl.TransactionDaoImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * @author zak zak@swingpulse.com>
 */
@Module
class DaoModule {

    @Singleton
    @Provides
    fun provideDatabase(application: Application): BudgetDatabase {
        return BudgetDatabase(application.applicationContext)
    }

    @Singleton
    @Provides
    fun provideAccountDao(database: BudgetDatabase): AccountDao {
        return AccountDaoImpl(database)
    }

    @Singleton
    @Provides
    fun provideCategoryDao(database: BudgetDatabase): CategoryDao {
        return CategoryDaoImpl(database)
    }

    @Singleton
    @Provides
    fun provideMoneyOperationDao(database: BudgetDatabase,
                                 accountDao: AccountDao,
                                 categoryDao: CategoryDao): TransactionDao {
        return TransactionDaoImpl(database, accountDao, categoryDao)
    }

    @Singleton
    @Provides
    fun provideTransactionContext(database: BudgetDatabase): TransactionContext {
        return TransactionContextImpl(database)
    }
}
