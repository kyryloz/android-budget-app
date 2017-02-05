package com.robotnec.budget.core.dao;

import com.robotnec.budget.core.domain.Transaction;

import java.util.List;

/**
 * @author zak <zak@swingpulse.com>
 */
public interface TransactionDao extends BaseDao<Transaction> {
    List<Transaction> getTransactionsForCategory(long categoryId);
}
