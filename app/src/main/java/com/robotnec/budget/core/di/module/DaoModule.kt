package com.robotnec.budget.core.di.module

import com.robotnec.budget.core.persistence.BudgetDatabase
import com.robotnec.budget.core.persistence.TransactionContext
import com.robotnec.budget.core.persistence.TransactionContextImpl
import com.robotnec.budget.core.persistence.dao.TransactionDao
import com.robotnec.budget.core.persistence.dao.impl.AccountDaoImpl
import com.robotnec.budget.core.persistence.dao.impl.CategoryDaoImpl
import com.robotnec.budget.core.persistence.dao.impl.TransactionDaoImpl
import com.robotnec.budget.core.persistence.dao.AccountDao
import com.robotnec.budget.core.persistence.dao.CategoryDao

import javax.inject.Singleton

import dagger.Module
import dagger.Provides

/**
 * @author zak zak@swingpulse.com>
 */
@Module
class DaoModule(private val database: BudgetDatabase) {

    @Singleton
    @Provides
    fun provideAccountDao(): AccountDao {
        return AccountDaoImpl(database)
    }

    @Singleton
    @Provides
    fun provideCategoryDao(): CategoryDao {
        return CategoryDaoImpl(database)
    }

    @Singleton
    @Provides
    fun provideMoneyOperationDao(accountDao: AccountDao,
                                 categoryDao: CategoryDao): TransactionDao {
        return TransactionDaoImpl(database, accountDao, categoryDao)
    }

    @Singleton
    @Provides
    fun provideTransactionContext(): TransactionContext {
        return TransactionContextImpl(database)
    }
}
