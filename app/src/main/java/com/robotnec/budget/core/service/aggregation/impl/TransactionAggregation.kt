package com.robotnec.budget.core.service.aggregation.impl

import com.robotnec.budget.core.domain.MoneyAmount
import com.robotnec.budget.core.domain.operation.Transaction
import java.util.SortedMap

/**
 * @author zak zak@swingpulse.com>
 */
interface TransactionAggregation {
    val spansCount: Int

    operator fun get(sorting: Sorting): SortedMap<TimeSpan, List<Transaction>>

    fun getSum(span: TimeSpan): MoneyAmount

    enum class Sorting {
        ASC, DESC
    }
}
