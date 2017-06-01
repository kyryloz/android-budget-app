package com.robotnec.budget.core.mvp.view


import com.robotnec.budget.core.service.aggregation.impl.TransactionAggregation

/**
 * @author zak zak@swingpulse.com>
 */
interface TransactionsView : View {
    fun displayTransactions(aggregation: TransactionAggregation)
}
