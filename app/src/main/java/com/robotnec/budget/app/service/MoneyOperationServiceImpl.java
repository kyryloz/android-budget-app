package com.robotnec.budget.app.service;

import com.robotnec.budget.core.dao.AccountDao;
import com.robotnec.budget.core.dao.MoneyOperationDao;
import com.robotnec.budget.core.domain.money.MoneyOperation;
import com.robotnec.budget.core.service.CurrencyExchangeService;
import com.robotnec.budget.core.service.MoneyOperationService;

import javax.inject.Inject;

/**
 * @author zak <zak@swingpulse.com>
 */
public class MoneyOperationServiceImpl implements MoneyOperationService {

    private final MoneyOperationDao moneyOperationDao;
    private final AccountDao accountDao;
    private final CurrencyExchangeService exchangeService;

    @Inject
    public MoneyOperationServiceImpl(MoneyOperationDao moneyOperationDao,
                                     AccountDao accountDao,
                                     CurrencyExchangeService exchangeService) {
        this.moneyOperationDao = moneyOperationDao;
        this.accountDao = accountDao;
        this.exchangeService = exchangeService;
    }

    @Override
    public boolean execute(MoneyOperation operation) {
        return operation.execute(moneyOperationDao, accountDao, exchangeService);
    }
}
