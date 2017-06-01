package com.robotnec.budget.core.service.impl

import com.robotnec.budget.core.domain.operation.Operation
import com.robotnec.budget.core.domain.operation.OperationReceiver
import com.robotnec.budget.core.domain.operation.OperationReceiverImpl
import com.robotnec.budget.core.persistence.TransactionContext
import com.robotnec.budget.core.persistence.dao.AccountDao
import com.robotnec.budget.core.persistence.dao.TransactionDao
import com.robotnec.budget.core.service.CurrencyExchangeService
import com.robotnec.budget.core.service.MoneyOperationBroker

/**
 * @author zak zak@swingpulse.com>
 */
class MoneyOperationBrokerImpl(transactionDao: TransactionDao,
                               accountDao: AccountDao,
                               exchangeService: CurrencyExchangeService,
                               transactionContext: TransactionContext) : MoneyOperationBroker {

    private val operationReceiver: OperationReceiver

    init {
        this.operationReceiver = OperationReceiverImpl(
                transactionDao,
                accountDao,
                exchangeService,
                transactionContext)
    }

    override fun execute(operation: Operation): Boolean {
        return operation.execute(operationReceiver)
    }
}
