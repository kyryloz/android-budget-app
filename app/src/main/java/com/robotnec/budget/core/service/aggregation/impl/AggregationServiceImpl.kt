package com.robotnec.budget.core.service.aggregation.impl

import com.robotnec.budget.core.domain.Currency
import com.robotnec.budget.core.domain.MoneyAmount
import com.robotnec.budget.core.domain.operation.Transaction
import com.robotnec.budget.core.service.aggregation.AggregationService
import java.util.*

/**
 * @author zak zak@swingpulse.com>
 */
class AggregationServiceImpl : AggregationService {

    override fun aggregate(transactions: List<Transaction>, resolution: AggregationService.Resolution): TransactionAggregation {
        val aggregatedMap = TreeMap<TimeSpan, List<Transaction>>()
        val sums = HashMap<TimeSpan, MoneyAmount>()
        if (transactions.isEmpty()) {
            return TransactionAggregationImpl.from(aggregatedMap, sums)
        }

        val minDate = transactions.map { it.date }.min()
        val maxDate = transactions.map { it.date }.max()

        if (minDate != null && maxDate != null) {
            val timeSpans = TimeSpans.byDays(minDate, maxDate)

            transactions.sortedBy { it.date }

            for (timeSpan in timeSpans) {
                val values = transactions
                        .filter { (_, _, date) -> timeSpan.isInSpan(date) }
                        .toList()
                val sum = transactions
                        .map { it.amount }
                        .fold(MoneyAmount.of(0.0, Currency.UAH), { obj, other -> obj.add(other) })
                if (!values.isEmpty()) {
                    aggregatedMap.put(timeSpan, values)
                    sums.put(timeSpan, sum)
                }
            }
        }
        return TransactionAggregationImpl.from(aggregatedMap, sums)
    }
}
