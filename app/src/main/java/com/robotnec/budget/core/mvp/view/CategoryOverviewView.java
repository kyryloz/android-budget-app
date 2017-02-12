package com.robotnec.budget.core.mvp.view;

import com.robotnec.budget.core.service.aggregation.impl.TransactionAggregation;

/**
 * @author zak <zak@swingpulse.com>
 */
public interface CategoryOverviewView extends View {
    void displayCategoryTitle(String title);

    void displayCategoryTransactions(TransactionAggregation transactionAggregation);
}
