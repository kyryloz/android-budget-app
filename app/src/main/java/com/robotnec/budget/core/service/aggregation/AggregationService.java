package com.robotnec.budget.core.service.aggregation;

import com.robotnec.budget.core.domain.operation.Transaction;
import com.robotnec.budget.core.service.aggregation.impl.TransactionAggregation;

import java.util.List;

/**
 * @author zak <zak@swingpulse.com>
 */
public interface AggregationService {

    TransactionAggregation aggregate(List<Transaction> transactions, Resolution resolution);

    enum Resolution {
        DAY
    }
}
