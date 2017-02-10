package com.robotnec.budget.core.service.aggregation.impl;

import com.robotnec.budget.core.domain.operation.Transaction;
import com.robotnec.budget.core.service.aggregation.AggregationService;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zak <zak@swingpulse.com>
 */
public class AggregationServiceTest {

    private AggregationService aggregationService;

    @Test
    public void before() {
        aggregationService = new AggregationServiceImpl();
    }

    @Test
    public void shouldAggregateTransactionsByDays() {
        List<Transaction> transactions = new ArrayList<>();
        transactions.add(new Transaction());
    }
}