package com.robotnec.budget.core.service.aggregation.impl;

import com.robotnec.budget.core.domain.operation.Transaction;

import java.util.List;
import java.util.Map;

/**
 * @author zak <zak@swingpulse.com>
 */
public class TransactionAggregationImpl implements TransactionAggregation {

    private final Map<TimeSpan, List<Transaction>> aggregation;

    public static TransactionAggregationImpl from(Map<TimeSpan, List<Transaction>> aggregation) {
        return new TransactionAggregationImpl(aggregation);
    }

    private TransactionAggregationImpl(Map<TimeSpan, List<Transaction>> aggregation) {
        this.aggregation = aggregation;
    }

    @Override
    public int getSpansCount() {
        return aggregation.size();
    }

    @Override
    public Map<TimeSpan, List<Transaction>> getMap() {
        return aggregation;
    }
}
