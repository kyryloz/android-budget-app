package ua.com.zak.budgetswing.core.mvp.view;

import java.util.List;

import ua.com.zak.budgetswing.core.domain.Transaction;

/**
 * @author zak <zak@swingpulse.com>
 */
public interface TransactionsView extends View {
    void displayTransactions(List<Transaction> transactions);
}
