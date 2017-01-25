package com.robotnec.budget.core.mvp.view;

import java.util.List;

import com.robotnec.budget.core.domain.Transaction;

/**
 * @author zak <zak@swingpulse.com>
 */
public interface TransactionsView extends View {
    void displayTransactions(List<Transaction> transactions);
}
