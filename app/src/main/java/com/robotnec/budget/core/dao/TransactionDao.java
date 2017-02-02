package com.robotnec.budget.core.dao;

import java.util.List;

import com.robotnec.budget.core.domain.Transaction;

/**
 * @author zak <zak@swingpulse.com>
 */
public interface TransactionDao {
    List<Transaction> getAllTransactions();

    boolean addTransaction(Transaction transaction);

    List<Transaction> getTransactionsForCategory(long categoryId);
}
