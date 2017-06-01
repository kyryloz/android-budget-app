package com.robotnec.budget.core.service.aggregation.impl

import com.robotnec.budget.core.domain.MoneyAmount
import com.robotnec.budget.core.domain.operation.Transaction
import java.util.*

/**
 * @author zak zak@swingpulse.com>
 */
internal class TransactionAggregationImpl private constructor(
        private val aggregation: SortedMap<TimeSpan, List<Transaction>>,
        private val sums: Map<TimeSpan, MoneyAmount>) : TransactionAggregation {

    init {
        for (timeSpan in this.aggregation.keys) {
            val unmodifiable = Collections.unmodifiableList(this.aggregation[timeSpan])
            this.aggregation.put(timeSpan, unmodifiable)
        }
    }

    override val spansCount: Int
        get() = aggregation.size

    override fun get(sorting: TransactionAggregation.Sorting): SortedMap<TimeSpan, List<Transaction>> {
        if (sorting === TransactionAggregation.Sorting.ASC) {
            return Collections.unmodifiableSortedMap(aggregation)
        } else {
            val sortedMap = TreeMap<TimeSpan, List<Transaction>> { o1, o2 -> o2.compareTo(o1) }
            sortedMap.putAll(aggregation)
            for (timeSpan in sortedMap.keys) {
                val sortedTransactions = ArrayList(sortedMap[timeSpan])
                sortedTransactions.sortedBy { it.date }
                sortedMap.put(timeSpan, sortedTransactions)
            }
            return Collections.unmodifiableSortedMap(sortedMap)
        }
    }

    override fun getSum(span: TimeSpan): MoneyAmount {
        return sums[span]!!
    }

    companion object {

        fun from(aggregation: SortedMap<TimeSpan, List<Transaction>>,
                 sums: Map<TimeSpan, MoneyAmount>): TransactionAggregationImpl {
            return TransactionAggregationImpl(aggregation, sums)
        }
    }
}
