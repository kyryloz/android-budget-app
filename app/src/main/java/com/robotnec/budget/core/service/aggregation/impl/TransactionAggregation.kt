package com.robotnec.budget.core.service.aggregation.impl

import com.robotnec.budget.core.domain.operation.Transaction
import org.joda.money.Money
import java.util.SortedMap

/**
 * @author zak zak@swingpulse.com>
 */
interface TransactionAggregation {
    val spansCount: Int

    operator fun get(sorting: Sorting): SortedMap<TimeSpan, List<Transaction>>

    fun getSum(span: TimeSpan): Money

    enum class Sorting {
        ASC, DESC
    }
}
