package com.robotnec.budget.core.service.aggregation.impl;

import android.support.annotation.NonNull;

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

        Assert.assertEquals(aggregation.getNodesCount(), 4);
    }

    @NonNull
    private Transaction createTransaction(int year, int month, int day, int hour, int minute) {
        Transaction t = new Transaction();
        t.setAmount(MoneyAmount.of(1, Currency.UAH));
        t.setDate(LocalDateTime.of(year, month, day, hour, minute));
        return t;
    }
}