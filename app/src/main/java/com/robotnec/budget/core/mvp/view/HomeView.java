package com.robotnec.budget.core.mvp.view;

import java.util.List;

import com.robotnec.budget.core.domain.Account;
import com.robotnec.budget.core.service.aggregation.impl.TransactionAggregation;

/**
 * @author zak <zak@swingpulse.com>
 */
public interface HomeView extends View {
    void displayAccounts(List<Account> accounts);

    void displayTransactions(TransactionAggregation aggregation);
}
