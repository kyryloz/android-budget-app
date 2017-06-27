package com.robotnec.budget.core.service.aggregation.impl

import com.robotnec.budget.core.domain.Account
import com.robotnec.budget.core.domain.Category
import com.robotnec.budget.core.domain.operation.Transaction
import com.robotnec.budget.core.service.aggregation.AggregationService

import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.threeten.bp.LocalDateTime

import java.util.ArrayList

/**
 * @author zak zak@swingpulse.com>
 */
class AggregationServiceTest {

    private lateinit var aggregationService: AggregationService

    @Before
    fun before() {
        aggregationService = AggregationServiceImpl()
    }

    @Test
    fun shouldAggregateTransactionsByDays() {
        val transactions = ArrayList<Transaction>()

        val t1 = createTransaction(2017, 1, 1, 12, 45)
        val t2 = createTransaction(2017, 1, 1, 23, 59)
        val t3 = createTransaction(2017, 1, 3, 0, 0)
        val t4 = createTransaction(2017, 1, 4, 12, 45)

        transactions.add(t1)
        transactions.add(t2)
        transactions.add(t3)
        transactions.add(t4)

        val aggregation = aggregationService.aggregate(transactions, AggregationService.Resolution.DAY)

        Assert.assertEquals(aggregation.spansCount.toLong(), 3)

        val map = aggregation[TransactionAggregation.Sorting.ASC]

        val expectedFirstSpan = TimeSpan.of(LocalDateTime.of(2017, 1, 1, 0, 0),
                LocalDateTime.of(2017, 1, 2, 0, 0))

        // first span
        val expectedFirstSpanTransactions = ArrayList<Transaction>()
        expectedFirstSpanTransactions.add(t1)
        expectedFirstSpanTransactions.add(t2)

        Assert.assertEquals(map[expectedFirstSpan], expectedFirstSpanTransactions)

        val expectedSecondSpan = TimeSpan.of(LocalDateTime.of(2017, 1, 3, 0, 0),
                LocalDateTime.of(2017, 1, 4, 0, 0))

        // second span
        val expectedSecondSpanTransactions = ArrayList<Transaction>()
        expectedSecondSpanTransactions.add(t3)

        Assert.assertEquals(map[expectedSecondSpan], expectedSecondSpanTransactions)

        // third span
        val expectedThirdSpan = TimeSpan.of(LocalDateTime.of(2017, 1, 4, 0, 0),
                LocalDateTime.of(2017, 1, 5, 0, 0))

        val expectedThirdSpanTransactions = ArrayList<Transaction>()
        expectedThirdSpanTransactions.add(t4)

        Assert.assertEquals(map[expectedThirdSpan], expectedThirdSpanTransactions)
    }

    private fun createTransaction(year: Int, month: Int, day: Int, hour: Int, minute: Int): Transaction {
        return Transaction(
                -1,
                MoneyAmount.of(1.0, Currency.UAH),
                LocalDateTime.of(year, month, day, hour, minute),
                Account(-1, "account", MoneyAmount.of(0.0, Currency.UAH)),
                Category(-1, "category")
        )
    }
}