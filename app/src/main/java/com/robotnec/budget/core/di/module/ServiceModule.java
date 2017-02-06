package com.robotnec.budget.core.di.module;

import com.robotnec.budget.app.service.MoneyOperationServiceImpl;
import com.robotnec.budget.app.service.SimpleCurrencyExchangeService;
import com.robotnec.budget.core.dao.AccountDao;
import com.robotnec.budget.core.dao.MoneyOperationDao;
import com.robotnec.budget.core.service.CurrencyExchangeService;
import com.robotnec.budget.core.service.MoneyOperationService;

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
    public MoneyOperationService provideMoneyOperationService(MoneyOperationDao moneyOperationDao,
                                                              AccountDao accountDao,
                                                              CurrencyExchangeService exchangeService) {
        return new MoneyOperationServiceImpl(moneyOperationDao, accountDao, exchangeService);
    }

    @Singleton
    @Provides
    public CurrencyExchangeService provideCurrencyExchangeService() {
        return new SimpleCurrencyExchangeService();
    }
}
