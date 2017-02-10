package com.robotnec.budget.core.service.aggregation;

import com.robotnec.budget.core.domain.operation.MoneyOperation;

import java.util.List;

/**
 * @author zak <zak@swingpulse.com>
 */
public interface AggregationService {

    TransactionAggregation aggregate(List<MoneyOperation> transactions, Resolution resolution);

    enum Resolution {
        DAY
    }
}
