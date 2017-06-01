package com.robotnec.budget.core.domain.operation

import com.robotnec.budget.BaseRobolectricTest
import com.robotnec.budget.core.persistence.BudgetDatabase
import com.robotnec.budget.core.persistence.TransactionContext
import com.robotnec.budget.core.persistence.TransactionContextImpl
import com.robotnec.budget.core.persistence.dao.TransactionDao
import com.robotnec.budget.core.persistence.dao.impl.TransactionDaoImpl
import com.robotnec.budget.core.persistence.dao.impl.AccountDaoImpl
import com.robotnec.budget.core.persistence.dao.impl.CategoryDaoImpl
import com.robotnec.budget.core.service.impl.SimpleCurrencyExchangeService
import com.robotnec.budget.core.persistence.dao.AccountDao
import com.robotnec.budget.core.persistence.dao.CategoryDao
import com.robotnec.budget.core.domain.Account
import com.robotnec.budget.core.domain.Category
import com.robotnec.budget.core.domain.Currency
import com.robotnec.budget.core.domain.MoneyAmount
import com.robotnec.budget.core.service.CurrencyExchangeService

import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.robolectric.RuntimeEnvironment
import org.threeten.bp.LocalDateTime

/**
 * @author zak zak@swingpulse.com>
 */
class OperationReceiverImplTest : BaseRobolectricTest() {

    private lateinit var transactionDao: TransactionDao
    private lateinit var exchangeService: CurrencyExchangeService
    private lateinit var transactionContext: TransactionContext
    private lateinit var testAccount: Account
    private lateinit var testCategory: Category
    private lateinit var accountDao: AccountDao

    @Before
    @Throws(Exception::class)
    fun before() {
        val database = BudgetDatabase(RuntimeEnvironment.application)
        accountDao = AccountDaoImpl(database)
        val categoryDao = CategoryDaoImpl(database)
        exchangeService = SimpleCurrencyExchangeService()
        transactionContext = TransactionContextImpl(database)
        transactionDao = TransactionDaoImpl(database, accountDao, categoryDao)

        testAccount = Account(
                -1,
                "account",
                MoneyAmount.of(100.0, Currency.UAH)
        )
        accountDao.createOrUpdate(testAccount)

        testCategory = Category(
                -1,
                "category"
        )
        categoryDao.createOrUpdate(testCategory)
    }

    @Test
    @Throws(Exception::class)
    fun shouldPerformExpenseOperation() {
        val operationReceiver = OperationReceiverImpl(transactionDao,
                accountDao,
                exchangeService, transactionContext)
        val expense = Expense(
                MoneyAmount.of(10.0, Currency.UAH),
                LocalDateTime.now(),
                testAccount,
                testCategory
        )

        val success = operationReceiver.receive(expense)
        Assert.assertTrue(success)

        val actual = accountDao.findById(testAccount.id).amount
        val ninety = MoneyAmount.of(90.0, Currency.UAH)
        Assert.assertEquals(ninety, actual)

        val actualOperations = transactionDao.all
        Assert.assertEquals(1, actualOperations.size.toLong())

        val (_, actualOperationAmount) = actualOperations[0]

        val ten = MoneyAmount.of(10.0, Currency.UAH)
        Assert.assertEquals(ten, actualOperationAmount)
    }

    @Test
    @Throws(Exception::class)
    fun shouldRollbackAccountChangeIfFail() {
        val mockTransactionDao = Mockito.mock(TransactionDao::class.java)
        Mockito.`when`(mockTransactionDao.createOrUpdate(Mockito.any<Transaction>())).thenReturn(false)
        val operationReceiver = OperationReceiverImpl(mockTransactionDao,
                accountDao,
                exchangeService, transactionContext)

        val expense = Expense(
                MoneyAmount.of(10.0, Currency.UAH),
                LocalDateTime.now(),
                testAccount,
                testCategory
        )

        val success = operationReceiver.receive(expense)
        Assert.assertFalse(success)

        val actual = accountDao.findById(testAccount.id).amount
        val ninety = MoneyAmount.of(90.0, Currency.UAH)
        Assert.assertNotEquals(ninety, actual)

        val actualOperations = transactionDao.all
        Assert.assertEquals(0, actualOperations.size.toLong())
    }
}