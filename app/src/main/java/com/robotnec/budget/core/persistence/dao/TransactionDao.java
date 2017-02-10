package com.robotnec.budget.core.persistence.dao;

import com.robotnec.budget.core.domain.operation.Transaction;

import java.util.List;

/**
 * @author zak <zak@swingpulse.com>
 */
public interface TransactionDao extends BaseDao<Transaction> {
    List<Transaction> getTransactionsForCategory(long categoryId);
}
