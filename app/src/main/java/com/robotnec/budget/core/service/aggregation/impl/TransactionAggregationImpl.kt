package com.robotnec.budget.core.service.aggregation.impl

import com.robotnec.budget.core.domain.operation.Transaction
import org.joda.money.Money
import java.util.ArrayList
import java.util.Collections
import java.util.SortedMap
import java.util.TreeMap

/**
 * @author zak zak@swingpulse.com>
 */
internal class TransactionAggregationImpl private constructor(
        private val aggregation: SortedMap<TimeSpan, List<Transaction>>,
        private val sums: Map<TimeSpan, Money>) : TransactionAggregation {

    init {
        for (timeSpan in this.aggregation.keys) {
            val unmodifiable = Collections.unmodifiableList(this.aggregation[timeSpan])
            this.aggregation[timeSpan] = unmodifiable
        }
    }

    override val spansCount: Int
        get() = aggregation.size

    override fun get(sorting: TransactionAggregation.Sorting): SortedMap<TimeSpan, List<Transaction>> {
        return if (sorting === TransactionAggregation.Sorting.ASC) {
            Collections.unmodifiableSortedMap(aggregation)
        } else {
            val sortedMap = TreeMap<TimeSpan, List<Transaction>> { o1, o2 -> o2.compareTo(o1) }
            sortedMap.putAll(aggregation)
            for (timeSpan in sortedMap.keys) {
                val sortedTransactions = ArrayList(sortedMap[timeSpan])
                sortedTransactions.sortedBy { it.date }
                sortedMap[timeSpan] = sortedTransactions
            }
            Collections.unmodifiableSortedMap(sortedMap)
        }
    }

    override fun getSum(span: TimeSpan): Money {
        return sums[span]!!
    }

    companion object {

        fun from(aggregation: SortedMap<TimeSpan, List<Transaction>>,
                 sums: Map<TimeSpan, Money>): TransactionAggregationImpl {
            return TransactionAggregationImpl(aggregation, sums)
        }
    }
}
