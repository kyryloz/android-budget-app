package com.robotnec.budget.core.di.module

import com.robotnec.budget.core.persistence.TransactionContext
import com.robotnec.budget.core.persistence.dao.AccountDao
import com.robotnec.budget.core.persistence.dao.TransactionDao
import com.robotnec.budget.core.service.CurrencyExchangeService
import com.robotnec.budget.core.service.MoneyOperationBroker
import com.robotnec.budget.core.service.aggregation.AggregationService
import com.robotnec.budget.core.service.aggregation.impl.AggregationServiceImpl
import com.robotnec.budget.core.service.impl.MoneyOperationBrokerImpl
import com.robotnec.budget.core.service.impl.SimpleCurrencyExchangeService

import javax.inject.Singleton

import dagger.Module
import dagger.Provides

/**
 * @author zak zak@swingpulse.com>
 */
@Module
class ServiceModule {

    @Singleton
    @Provides
    fun provideMoneyOperationService(transactionDao: TransactionDao,
                                     accountDao: AccountDao,
                                     exchangeService: CurrencyExchangeService,
                                     transactionContext: TransactionContext): MoneyOperationBroker {
        return MoneyOperationBrokerImpl(transactionDao,
                accountDao, exchangeService, transactionContext)
    }

    @Singleton
    @Provides
    fun provideCurrencyExchangeService(): CurrencyExchangeService {
        return SimpleCurrencyExchangeService()
    }

    @Singleton
    @Provides
    fun provideAggregationService(): AggregationService {
        return AggregationServiceImpl()
    }
}
