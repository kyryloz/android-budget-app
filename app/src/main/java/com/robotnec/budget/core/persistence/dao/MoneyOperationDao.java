package com.robotnec.budget.core.persistence.dao;

import com.robotnec.budget.core.domain.operation.MoneyOperation;

import java.util.List;

/**
 * @author zak <zak@swingpulse.com>
 */
public interface MoneyOperationDao extends BaseDao<MoneyOperation> {
    List<MoneyOperation> getTransactionsForCategory(long categoryId);
}
