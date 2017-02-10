package com.robotnec.budget.core.mvp.view;


import com.robotnec.budget.core.service.aggregation.TransactionAggregation;

/**
 * @author zak <zak@swingpulse.com>
 */
public interface TransactionsView extends View {
    void displayTransactions(TransactionAggregation aggregation);
}
