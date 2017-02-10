package com.robotnec.budget.core.di.module;

import com.robotnec.budget.core.persistence.TransactionContext;
import com.robotnec.budget.core.persistence.dao.AccountDao;
import com.robotnec.budget.core.persistence.dao.MoneyOperationDao;
import com.robotnec.budget.core.service.CurrencyExchangeService;
import com.robotnec.budget.core.service.MoneyOperationBroker;
import com.robotnec.budget.core.service.aggregation.AggregationService;
import com.robotnec.budget.core.service.aggregation.impl.AggregationServiceImpl;
import com.robotnec.budget.core.service.impl.MoneyOperationBrokerImpl;
import com.robotnec.budget.core.service.impl.SimpleCurrencyExchangeService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * @author zak <zak@swingpulse.com>
 */
@Module
public class ServiceModule {

    @Singleton
    @Provides
    public MoneyOperationBroker provideMoneyOperationService(MoneyOperationDao moneyOperationDao,
                                                             AccountDao accountDao,
                                                             CurrencyExchangeService exchangeService,
                                                             TransactionContext transactionContext) {
        return new MoneyOperationBrokerImpl(moneyOperationDao,
                accountDao, exchangeService, transactionContext);
    }

    @Singleton
    @Provides
    public CurrencyExchangeService provideCurrencyExchangeService() {
        return new SimpleCurrencyExchangeService();
    }

    @Singleton
    @Provides
    public AggregationService provideAggregationService() {
        return new AggregationServiceImpl();
    }
}
