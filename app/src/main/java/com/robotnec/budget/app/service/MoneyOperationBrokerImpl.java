package com.robotnec.budget.app.service;

import com.robotnec.budget.core.dao.AccountDao;
import com.robotnec.budget.core.dao.MoneyOperationDao;
import com.robotnec.budget.core.domain.operation.Operation;
import com.robotnec.budget.core.domain.operation.OperationReceiver;
import com.robotnec.budget.core.domain.operation.OperationReceiverImpl;
import com.robotnec.budget.core.service.CurrencyExchangeService;
import com.robotnec.budget.core.service.MoneyOperationBroker;

/**
 * @author zak <zak@swingpulse.com>
 */
public class MoneyOperationBrokerImpl implements MoneyOperationBroker {

    private final OperationReceiver operationReceiver;

    public MoneyOperationBrokerImpl(MoneyOperationDao moneyOperationDao,
                                    AccountDao accountDao,
                                    CurrencyExchangeService exchangeService) {
        this.operationReceiver = new OperationReceiverImpl(moneyOperationDao, accountDao, exchangeService);
    }

    @Override
    public boolean execute(Operation operation) {
        return operation.execute(operationReceiver);
    }
}
