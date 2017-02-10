package com.robotnec.budget.core.service.impl;

import com.robotnec.budget.core.persistence.TransactionContext;
import com.robotnec.budget.core.persistence.dao.AccountDao;
import com.robotnec.budget.core.persistence.dao.TransactionDao;
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

    public MoneyOperationBrokerImpl(TransactionDao transactionDao,
                                    AccountDao accountDao,
                                    CurrencyExchangeService exchangeService,
                                    TransactionContext transactionContext) {
        this.operationReceiver = new OperationReceiverImpl(transactionDao,
                accountDao, exchangeService, transactionContext);
    }

    @Override
    public boolean execute(Operation operation) {
        return operation.execute(operationReceiver);
    }
}
