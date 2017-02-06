package com.robotnec.budget.core.dao;

import com.robotnec.budget.core.domain.money.MoneyOperation;

import java.util.List;

/**
 * @author zak <zak@swingpulse.com>
 */
public interface MoneyOperationDao extends BaseDao<MoneyOperation> {
    List<MoneyOperation> getTransactionsForCategory(long categoryId);
}
