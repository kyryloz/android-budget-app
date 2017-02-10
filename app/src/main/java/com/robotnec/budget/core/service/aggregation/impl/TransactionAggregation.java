package com.robotnec.budget.core.service.aggregation.impl;

import com.robotnec.budget.core.domain.operation.Transaction;

import java.util.List;
import java.util.Map;

/**
 * @author zak <zak@swingpulse.com>
 */
public interface TransactionAggregation {
    int getSpansCount();

    Map<TimeSpan, List<Transaction>> getMap();
}
