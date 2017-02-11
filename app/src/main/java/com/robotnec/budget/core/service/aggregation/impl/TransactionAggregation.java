package com.robotnec.budget.core.service.aggregation.impl;

import com.robotnec.budget.core.domain.operation.Transaction;

import java.util.List;
import java.util.SortedMap;

/**
 * @author zak <zak@swingpulse.com>
 */
public interface TransactionAggregation {
    int getSpansCount();

    SortedMap<TimeSpan, List<Transaction>> get(Sorting sorting);

    enum Sorting {
        ASC, DESC
    }
}
