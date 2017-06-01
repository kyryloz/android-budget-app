package com.robotnec.budget.core.service.aggregation

import com.robotnec.budget.core.domain.operation.Transaction
import com.robotnec.budget.core.service.aggregation.impl.TransactionAggregation

/**
 * @author zak zak@swingpulse.com>
 */
interface AggregationService {

    fun aggregate(transactions: List<Transaction>, resolution: Resolution): TransactionAggregation

    enum class Resolution {
        DAY
    }
}
