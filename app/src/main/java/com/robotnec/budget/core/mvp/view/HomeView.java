package com.robotnec.budget.core.mvp.view;

import com.robotnec.budget.core.domain.Account;
import com.robotnec.budget.core.service.aggregation.impl.TransactionAggregation;

import java.util.List;

/**
 * @author zak <zak@swingpulse.com>
 */
public interface HomeView extends View {
    void displayAccountsWithTransactions(List<Account> accounts,
                                         TransactionAggregation aggregation);
}
