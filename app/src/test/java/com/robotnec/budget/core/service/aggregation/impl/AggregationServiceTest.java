package com.robotnec.budget.core.service.aggregation.impl;

import android.support.annotation.NonNull;

import com.robotnec.budget.core.domain.Account;
import com.robotnec.budget.core.domain.Category;
import com.robotnec.budget.core.domain.Currency;
import com.robotnec.budget.core.domain.MoneyAmount;
import com.robotnec.budget.core.domain.operation.Transaction;
import com.robotnec.budget.core.service.aggregation.AggregationService;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.threeten.bp.LocalDateTime;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedMap;

/**
 * @author zak <zak@swingpulse.com>
 */
public class AggregationServiceTest {

    private AggregationService aggregationService;

    @Before
    public void before() {
        aggregationService = new AggregationServiceImpl();
    }

    @Test
    public void shouldAggregateTransactionsByDays() {
        List<Transaction> transactions = new ArrayList<>();

        Transaction t1 = createTransaction(2017, 1, 1, 12, 45);
        Transaction t2 = createTransaction(2017, 1, 1, 23, 59);
        Transaction t3 = createTransaction(2017, 1, 3, 0, 0);
        Transaction t4 = createTransaction(2017, 1, 4, 12, 45);

        transactions.add(t1);
        transactions.add(t2);
        transactions.add(t3);
        transactions.add(t4);

        TransactionAggregation aggregation =
                aggregationService.aggregate(transactions, AggregationService.Resolution.DAY);

        Assert.assertEquals(aggregation.getSpansCount(), 3);

        SortedMap<TimeSpan, List<Transaction>> map =
                aggregation.get(TransactionAggregation.Sorting.ASC);

        TimeSpan expectedFirstSpan = TimeSpan.Companion.of(LocalDateTime.of(2017, 1, 1, 0, 0),
                LocalDateTime.of(2017, 1, 2, 0, 0));

        // first span
        List<Transaction> expectedFirstSpanTransactions = new ArrayList<>();
        expectedFirstSpanTransactions.add(t1);
        expectedFirstSpanTransactions.add(t2);

        Assert.assertEquals(map.get(expectedFirstSpan), expectedFirstSpanTransactions);

        TimeSpan expectedSecondSpan = TimeSpan.Companion.of(LocalDateTime.of(2017, 1, 3, 0, 0),
                LocalDateTime.of(2017, 1, 4, 0, 0));

        // second span
        List<Transaction> expectedSecondSpanTransactions = new ArrayList<>();
        expectedSecondSpanTransactions.add(t3);

        Assert.assertEquals(map.get(expectedSecondSpan), expectedSecondSpanTransactions);

        // third span
        TimeSpan expectedThirdSpan = TimeSpan.Companion.of(LocalDateTime.of(2017, 1, 4, 0, 0),
                LocalDateTime.of(2017, 1, 5, 0, 0));

        List<Transaction> expectedThirdSpanTransactions = new ArrayList<>();
        expectedThirdSpanTransactions.add(t4);

        Assert.assertEquals(map.get(expectedThirdSpan), expectedThirdSpanTransactions);
    }

    @NonNull
    private Transaction createTransaction(int year, int month, int day, int hour, int minute) {
        return new Transaction(
                -1,
                MoneyAmount.Companion.of(1, Currency.UAH),
                LocalDateTime.of(year, month, day, hour, minute),
                new Account(-1, "account", MoneyAmount.Companion.of(0, Currency.UAH)),
                new Category(-1, "category")
        );
    }
}