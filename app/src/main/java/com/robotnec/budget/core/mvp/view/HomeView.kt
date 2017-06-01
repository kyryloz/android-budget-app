package com.robotnec.budget.core.mvp.view

import com.robotnec.budget.core.domain.Account
import com.robotnec.budget.core.service.aggregation.impl.TransactionAggregation

/**
 * @author zak zak@swingpulse.com>
 */
interface HomeView : View {
    fun displayAccountsWithTransactions(accounts: List<Account>,
                                        aggregation: TransactionAggregation)
}
