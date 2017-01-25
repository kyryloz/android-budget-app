package ua.com.zak.budgetswing.core.dao;

import java.util.List;

import ua.com.zak.budgetswing.core.domain.Transaction;

/**
 * @author zak <zak@swingpulse.com>
 */
public interface TransactionDao {
    List<Transaction> getAllTransactions();
    boolean addTransaction(Transaction transaction);
}
