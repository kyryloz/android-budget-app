package com.robotnec.budget.core.mvp.view

import com.robotnec.budget.core.service.aggregation.impl.TransactionAggregation

/**
 * @author zak zak@swingpulse.com>
 */
interface CategoryOverviewView : View {
    fun displayCategoryTitle(title: String)

    fun displayCategoryTransactions(transactionAggregation: TransactionAggregation)
}
