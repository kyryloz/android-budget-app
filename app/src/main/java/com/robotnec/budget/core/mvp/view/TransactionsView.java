package com.robotnec.budget.core.mvp.view;


import com.robotnec.budget.core.domain.operation.MoneyOperation;

import java.util.List;

/**
 * @author zak <zak@swingpulse.com>
 */
public interface TransactionsView extends View {
    void displayTransactions(List<MoneyOperation> transactions);
}
