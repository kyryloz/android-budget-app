package com.robotnec.budget.core.domain.operation

import com.robotnec.budget.core.domain.Category
import com.robotnec.budget.core.persistence.TransactionContext
import com.robotnec.budget.core.persistence.dao.AccountDao
import com.robotnec.budget.core.persistence.dao.TransactionDao
import com.robotnec.budget.core.service.CurrencyExchangeService
import org.joda.money.CurrencyUnit
import org.joda.money.Money
import org.threeten.bp.LocalDateTime

/**
 * @author zak zak@swingpulse.com>
 */
class OperationReceiverImpl(private val transactionDao: TransactionDao,
                            private val accountDao: AccountDao,
                            private val exchangeService: CurrencyExchangeService,
                            private val transactionContext: TransactionContext) : OperationReceiver {

    override fun receive(expense: Expense): Boolean {
        return transactionContext.doInTransaction {
            val amount = expense.amount
            val targetAccount = expense.account
            val exchanged = exchangeService.exchange(amount, targetAccount.amount.currencyUnit)
            targetAccount.amount = targetAccount.amount.minus(exchanged)
            val accountUpdated = accountDao.createOrUpdate(targetAccount)
            val entity = Transaction(
                    amount = expense.amount,
                    date = expense.date,
                    account = expense.account,
                    category = expense.category
            )
            val moneyOperationUpdated = transactionDao.createOrUpdate(entity)
            accountUpdated && moneyOperationUpdated
        }
    }

    override fun receive(income: Income): Boolean {
        val account = income.account
        val bonus = Money.of(CurrencyUnit.USD, 100.0)
        account.amount = account.amount.plus(bonus)
        accountDao.createOrUpdate(account)
        val entity = Transaction(
                amount = bonus,
                date = LocalDateTime.now(),
                account = income.account,
                category = Category(1, "test")
        )
        entity.account = income.account
        entity.amount = bonus
        return transactionDao.createOrUpdate(entity)
    }
}
